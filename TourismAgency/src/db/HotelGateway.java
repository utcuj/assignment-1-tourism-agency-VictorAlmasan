package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.HotelGatewayException;
import exception.UserGatewayException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Hotel;
import model.HotelRating;
import model.User;
import model.UserType;

public class HotelGateway {
	
	private final static String findStatementString = "SELECT h.hotel_id, h.hotel_rating_code, h.location, h.name, " +
	" hr.hotel_rating_code, hr.quality FROM hotels h" +
	" INNER JOIN hotel_ratings hr ON h.hotel_rating_code = hr.hotel_rating_code;";
	
	public static ObservableList<Hotel> getHotelList() throws HotelGatewayException {
		
		ObservableList<Hotel> hotelsData = FXCollections.observableArrayList();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			PreparedStatement st = dbConnection.prepareStatement(findStatementString); 
			rs = st.executeQuery();
			while (rs.next()) {
			HotelRating hotelRating = new HotelRating(rs.getString("hr.hotel_rating_code"), rs.getString("hr.quality"));
			Hotel hotel = new Hotel(rs.getInt("h.hotel_id"), hotelRating, rs.getString("h.location"), rs.getString("h.name"));
			hotelsData.add(hotel);
			}
		} catch(SQLException e) {
			throw new HotelGatewayException("Hotel: get ", e);
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return hotelsData;
	}
}
