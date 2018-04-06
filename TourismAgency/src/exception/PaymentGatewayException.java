package exception;

public class PaymentGatewayException extends Exception{

	public PaymentGatewayException(String string, Exception exception) {
		System.out.println(string);
		System.out.println(exception.getMessage());
	}
}
