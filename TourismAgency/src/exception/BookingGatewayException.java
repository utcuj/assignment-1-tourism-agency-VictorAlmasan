package exception;

public class BookingGatewayException extends Exception {

	public BookingGatewayException(String string, Exception exception) {
		System.out.println(string);
		System.out.println(exception.getMessage());
	}
}
