package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import exception.BookingGatewayException;
import exception.CustomerGatewayException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Booking;
import model.BookingOutcome;
import model.Customer;
import model.Hotel;
import model.HotelRating;

public class BookingGateway {

	private final static String findStatementString = "SELECT b.booking_id, b.booking_outcome_code, b.hotel_id, b.main_customer_id, b.total_price, b.final_payment_date," + 
			"bo.booking_outcome_code, bo.booking_outcome_description, h.hotel_id, h.hotel_rating_code, h.location, h.name, hr.hotel_rating_code, hr.quality," + 
			"c.customer_id, c.name, c.cnp, c.address, (b.total_price - ifnull((SELECT sum(payment_amount) FROM payments WHERE booking_id = b.booking_id), 0)) as toPay " + 
			"FROM bookings b " + 
			"INNER JOIN booking_outcomes bo ON b.booking_outcome_code = bo.booking_outcome_code " + 
			"INNER JOIN hotels h ON b.hotel_id = h.hotel_id " + 
			"INNER JOIN hotel_ratings hr ON h.hotel_rating_code = hr.hotel_rating_code " + 
			"INNER JOIN customers c ON b.main_customer_id = c.customer_id;";
	
	private final static String findStatementString2 = "SELECT b.booking_id, b.booking_outcome_code, b.hotel_id, b.main_customer_id, b.total_price, b.final_payment_date," + 
			"bo.booking_outcome_code, bo.booking_outcome_description, h.hotel_id, h.hotel_rating_code, h.location, h.name, hr.hotel_rating_code, hr.quality," + 
			"c.customer_id, c.name, c.cnp, c.address" + 
			"FROM bookings b" + 
			"INNER JOIN booking_outcomes bo ON b.booking_outcome_code = bo.booking_outcome_code" + 
			"INNER JOIN hotels h ON b.hotel_id = h.hotel_id" + 
			"INNER JOIN hotel_ratings hr ON h.hotel_rating_code = hr.hotel_rating_code" + 
			"INNER JOIN customers c ON b.main_customer_id = c.customer_id" +
			"WHERE main_customer_id = ?;";
	
	private final static String  insertStatementString = "INSERT INTO bookings (booking_outcome_code, hotel_id, main_customer_id, "
			+ "total_price, final_payment_date) VALUES (?, ?, ?, ?, ?);";
	private final static String insertStatementString2 = "INSERT INTO tourists (customer_id, booking_id) VALUES (?, ?);";
	
	private final static String editStatementString = "UPDATE bookings SET total_price = ? WHERE booking_id = ?;";
	private final static String editStatementString2 = "UPDATE bookings SET booking_outcome_code = ? WHERE booking_id = ?;";
	
	private final static String deleteStatementString = "DELETE FROM bookings WHERE booking_id = ?;";
	private final static String deleteStatementString2 = "DELETE FROM payments WHERE booking_id = ?;";
	private final static String deleteStatementString3 = "DELETE FROM tourists WHERE booking_id = ?;";

	
	public static int insert(Booking booking, Customer customer)  throws BookingGatewayException {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, booking.getBookingOutcome().getBookingOutcomeCode());
			insertStatement.setInt(2, booking.getHotel().getHotelId());
			insertStatement.setInt(3, booking.getMainCustomer().getCustomerId());
			insertStatement.setInt(4, booking.getTotalPrice());
			insertStatement.setDate(5, Date.valueOf(booking.getFinalPaymentDate()));
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
			ConnectionFactory.close(insertStatement);
			
			
			insertStatement = dbConnection.prepareStatement(insertStatementString2);
			insertStatement.setInt(1, customer.getCustomerId());
			insertStatement.setInt(2, insertedId);
			insertStatement.executeUpdate();
			ConnectionFactory.close(insertStatement);
			
			insertStatement = dbConnection.prepareStatement(insertStatementString2);
			insertStatement.setInt(1, booking.getMainCustomer().getCustomerId());
			insertStatement.setInt(2, insertedId);
			insertStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new BookingGatewayException("Booking: insert ", e);
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	
	
	public static void update(int bookingId, int totalPrice) throws BookingGatewayException {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(editStatementString);
			findStatement.setInt(1, totalPrice);
			findStatement.setInt(2, bookingId);
			findStatement.executeUpdate();
		} catch (SQLException e) {
			throw new BookingGatewayException("Booking: edit ", e);
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	public static void updateBookingOutcomeCode(int bookingId, String bookingOutcomeCode) throws BookingGatewayException {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(editStatementString2);
			findStatement.setString(1, bookingOutcomeCode);
			findStatement.setInt(2, bookingId);
			findStatement.executeUpdate();
		} catch (SQLException e) {
			throw new BookingGatewayException("Booking: edit outcome code", e);
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
	}
	
	public static void deleteBooking(Booking booking) throws BookingGatewayException {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(deleteStatementString2);
			findStatement.setInt(1, booking.getBookingId());
			findStatement.executeUpdate();
			ConnectionFactory.close(findStatement);
			
			findStatement = dbConnection.prepareStatement(deleteStatementString3);
			findStatement.setInt(1, booking.getBookingId());
			findStatement.executeUpdate();
			ConnectionFactory.close(findStatement);
			
			findStatement = dbConnection.prepareStatement(deleteStatementString);
			findStatement.setInt(1, booking.getBookingId());
			findStatement.executeUpdate();
		} catch (SQLException e) {
			throw new BookingGatewayException("Booking: delete ", e);
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}

	}
	
	
public static ObservableList<Booking> getBookingList() throws BookingGatewayException {
		
		ObservableList<Booking> bookingsData = FXCollections.observableArrayList();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			PreparedStatement st = dbConnection.prepareStatement(findStatementString);
			rs = st.executeQuery();
			while (rs.next()) {
			BookingOutcome bookingOutcome = new BookingOutcome(rs.getString("bo.booking_outcome_code"), rs.getString("bo.booking_outcome_description"));
			HotelRating hotelRating = new HotelRating(rs.getString("hr.hotel_rating_code"), rs.getString("hr.quality"));
			Hotel hotel = new Hotel(rs.getInt("h.hotel_id"), hotelRating, rs.getString("h.location"), rs.getString("h.name"));
			Customer customer = new Customer(rs.getInt("c.customer_id"), rs.getString("c.name"), rs.getString("c.cnp"), rs.getString("c.address"));
			Booking booking = new Booking(rs.getInt("b.booking_id"),bookingOutcome, hotel, customer, rs.getInt("b.total_price"), rs.getDate("b.final_payment_date").toLocalDate(), rs.getInt("toPay"));
			
			bookingsData.add(booking);
			}
		} catch(SQLException e) {
			throw new BookingGatewayException("Booking: get list ", e);
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return bookingsData;
	}

public static ObservableList<Booking> getBookingListByCustomer(Customer customer) throws CustomerGatewayException {
	
	ObservableList<Booking> bookingsData = FXCollections.observableArrayList();
	
	Connection dbConnection = ConnectionFactory.getConnection();
	PreparedStatement findStatement = null;
	ResultSet rs = null;
	try {
		PreparedStatement st = dbConnection.prepareStatement(findStatementString2); 
		st.setInt(1, customer.getCustomerId());
		rs = st.executeQuery();
		while (rs.next()) {
		BookingOutcome bookingOutcome = new BookingOutcome(rs.getString("bo.booking_outcome_code"), rs.getString("bo.booking_outcome_description"));
		HotelRating hotelRating = new HotelRating(rs.getString("hr.hotel_rating_code"), rs.getString("hr.quality"));
		Hotel hotel = new Hotel(rs.getInt("h.hotel_id"), hotelRating, rs.getString("h.location"), rs.getString("h.name"));
		Customer customer2 = new Customer(rs.getInt("c.customer_id"), rs.getString("c.name"), rs.getString("c.cnp"), rs.getString("c.address"));
		Booking booking = new Booking(rs.getInt("b.booking_id"),bookingOutcome, hotel, customer2, rs.getInt("b.total_price"), rs.getDate("b.finale_payment_date").toLocalDate(), rs.getInt("toPay"));
		
		bookingsData.add(booking);
		}
	} catch(SQLException e) {
		throw new CustomerGatewayException("Customer: delete ", e);
	} finally {
		ConnectionFactory.close(rs);
		ConnectionFactory.close(findStatement);
		ConnectionFactory.close(dbConnection);
	}
	
	return bookingsData;
}
}
