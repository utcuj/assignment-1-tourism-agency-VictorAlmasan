package exception;

public class CustomerGatewayException extends Exception{
	
	public  CustomerGatewayException(String string, Exception exception) {
		System.out.println(string);
		System.out.println(exception.getMessage());
	}

}
