package exception;

public class LoggingGatewayException extends Exception{

	public  LoggingGatewayException(String string, Exception exception) {
		System.out.println(string);
		System.out.println(exception.getMessage());
	}
}
