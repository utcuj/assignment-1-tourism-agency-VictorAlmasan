package mapper;

import java.time.LocalDate;

import db.LoggingGateway;
import exception.LoggingGatewayException;

public class LoggingMapper {

	public static int insert(int userId, String operationTypeCode, LocalDate logDate) {
		int x = 0;
		try {
			x = LoggingGateway.insert(userId, operationTypeCode, logDate);
		} catch(LoggingGatewayException e) {
			System.out.println("Eroare la logging insert");
		}
		return x;
	}
}
