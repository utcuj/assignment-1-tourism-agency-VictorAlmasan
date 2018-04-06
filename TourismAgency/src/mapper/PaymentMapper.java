package mapper;

import db.PaymentGateway;
import exception.PaymentGatewayException;
import javafx.collections.ObservableList;
import model.Booking;
import model.Payment;

public class PaymentMapper {
	
	public static ObservableList<Payment> getPaymentList(Booking booking){
		ObservableList<Payment> list = null;
		try {
			list = PaymentGateway.getPaymentList(booking);
		} catch (PaymentGatewayException e) {
			System.out.println("Eroare la getPaymentList.");
		}
		return list;
	}
	
	public static int insert(Payment payment) {
		int x = 0;
		try {
			x = PaymentGateway.insert(payment);
		} catch (PaymentGatewayException e) {
			System.out.println("Eroare la insert payment.");
		}
		return x;
	}
}
