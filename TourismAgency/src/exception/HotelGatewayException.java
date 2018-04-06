package exception;

public class HotelGatewayException extends Exception{
	
	public HotelGatewayException(String string, Exception exception) {
		System.out.println(string);
		System.out.println(exception.getMessage());
	}

}
