package exception;

public class UserGatewayException extends Exception{

	public UserGatewayException(String string, Exception exception) {
		System.out.println(string);
		System.out.println(exception.getMessage());
	}
}
