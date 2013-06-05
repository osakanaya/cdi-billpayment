package billpayment.payment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Digits;

import billpayment.event.PaymentEvent;
import billpayment.interceptor.Logged;

@Named
@SessionScoped
public class PaymentBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(PaymentBean.class.getCanonicalName());
	
	private static final int DEBIT = 1;
	private static final int CREDIT = 2;
	
	@Inject @Credit
	Event<PaymentEvent> creditEvent;

	@Inject @Debit
	Event<PaymentEvent> debitEvent;
	
	@Digits(integer=10, fraction=2, message="Invalid value")
	private BigDecimal value;
	private Date datetime;
	private int paymentOption = DEBIT;
	
	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public int getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(int paymentOption) {
		this.paymentOption = paymentOption;
	}

	@Logged
	public String pay() {
		this.setDatetime(Calendar.getInstance().getTime());
		
		switch(paymentOption) {
		case DEBIT:
			PaymentEvent debitPayload = new PaymentEvent();
			debitPayload.setPaymentType("Debit");
			debitPayload.setValue(value);
			debitPayload.setDatetime(datetime);
			debitEvent.fire(debitPayload);
			
			break;
		case CREDIT:
			PaymentEvent creditPayload = new PaymentEvent();
			creditPayload.setPaymentType("Credit");
			creditPayload.setValue(value);
			creditPayload.setDatetime(datetime);
			creditEvent.fire(creditPayload);
			
			break;
		default:
			logger.severe("Invalid payment option!");
		}
		
		return "/response.xhtml";
	}
	
	@Logged
	public void reset() {
		setPaymentOption(DEBIT);
		setValue(BigDecimal.ZERO);
	}

}
