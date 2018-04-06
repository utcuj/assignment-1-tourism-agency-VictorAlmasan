package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import exception.CustomerGatewayException;
import exception.LoggingGatewayException;
import model.Customer;

public class LoggingGateway {

	private final static String insertStatementString = "INSERT INTO logging(user_id, operation_type_code, log_date) VALUES (?,?,?);";
	
	public static int insert(int userId, String operationTypeCode, LocalDate logDate)  throws LoggingGatewayException {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1,userId);
			insertStatement.setString(2, operationTypeCode);
			insertStatement.setDate(3, Date.valueOf(logDate));
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new LoggingGatewayException("Logging: insert ", e);
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
}
