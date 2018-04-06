package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.CustomerGatewayException;
import exception.PaymentGatewayException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Booking;
import model.Payment;

public class PaymentGateway {
	
	private static final String findStatementString = "SELECT payment_id, booking_id, payment_amount, payment_date WHERE payment_id = ?;";
	private static final String insertStatementString = "INSERT INTO payments (booking_id, payment_amount, payment_date) VALUES (?,?,?);";
	
	public static ObservableList<Payment> getPaymentList(Booking booking) throws PaymentGatewayException {
		
		ObservableList<Payment> paymentsData = FXCollections.observableArrayList();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			PreparedStatement st = dbConnection.prepareStatement(findStatementString); 
			st.setInt(1, booking.getBookingId());
			rs = st.executeQuery();
			while (rs.next()) {
			Payment payment = new Payment(rs.getInt("payment_id"), rs.getInt("booking_id"), rs.getInt("payment_amount"),rs.getDate("payment_date").toLocalDate());
			paymentsData.add(payment);
			}
		} catch(SQLException e) {
			throw new PaymentGatewayException("Payment: find by booking id ", e);
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return paymentsData;
	}
	
	
	public static int insert(Payment payment) throws PaymentGatewayException{
		
		// aici trebuie verificat daca data platii este mai mica decat final payment date din booking
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, payment.getBookingId());
			insertStatement.setInt(2, payment.getPaymentAmount());
			insertStatement.setDate(3, Date.valueOf(payment.getPaymentDate()));
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new PaymentGatewayException("Payment: insert ", e);
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		// aici trebuie sa calculez totalul pentru booking si daca e mai mare decat total_price setez booking outcome pe OK
		return insertedId;
	}

}
