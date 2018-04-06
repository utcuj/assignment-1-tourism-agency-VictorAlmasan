package mapper;

import db.HotelGateway;
import exception.HotelGatewayException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Hotel;

public class HotelMapper {

	public static ObservableList<Hotel> getHotelList() {

		ObservableList<Hotel> list = null;
		try {
			list = HotelGateway.getHotelList();
		} catch (HotelGatewayException e) {
			System.out.println("Eroare la hotels load.");
		}
		return list;
	}
}
