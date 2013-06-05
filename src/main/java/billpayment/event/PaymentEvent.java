package billpayment.event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PaymentEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	public BigDecimal value;
	public Date datetime;
	public String paymentType;
	
	public PaymentEvent() {}
	
    @Override
    public String toString() {
        return this.paymentType + " = $" + this.value.toString() + " at "
        + this.datetime.toString();
    }

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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
}
