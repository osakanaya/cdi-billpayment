package billpayment.listener;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;

import billpayment.event.PaymentEvent;
import billpayment.interceptor.Logged;
import billpayment.payment.Credit;
import billpayment.payment.Debit;

@Logged
@SessionScoped
public class PaymentHandler implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(PaymentHandler.class.getCanonicalName());
	
	public PaymentHandler() {
		logger.log(Level.INFO, "PaymentHandler Created.");
	}
	
	public void creditPayment(@Observes @Credit PaymentEvent event) {
		logger.log(Level.INFO, "PaymentHandler - Creadit Handler: {0}", event.toString());
	}
	
	public void debitPayment(@Observes @Debit PaymentEvent event) {
		logger.log(Level.INFO, "PaymentHandler - Debit Handler: {0}", event.toString());
	}
	
}
