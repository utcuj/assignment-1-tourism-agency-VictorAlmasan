package mapper;

import db.BookingGateway;
import exception.BookingGatewayException;
import javafx.collections.ObservableList;
import model.Booking;
import model.Customer;

public class BookingMapper {

	public static ObservableList<Booking> getBookingList() {
		ObservableList<Booking> list = null;
		try {
			list = BookingGateway.getBookingList();
		} catch(BookingGatewayException e) {
			System.out.println("Eroare la load bookings.");
		}
		return list;
	}
	
	public static void updateBookingOutcomeCode(int bookingId, String bookingOutcomeCode)  {
		try {
			BookingGateway.updateBookingOutcomeCode(bookingId, bookingOutcomeCode);
		} catch(BookingGatewayException e) {
			System.out.println("Eroare la edit booking outcome code.");
		}
	}
	
	public static void update(int bookingId, int totalPrice) {
		try {
			BookingGateway.update(bookingId, totalPrice);
		} catch(BookingGatewayException e) {
			System.out.println("Eroare la edit booking total price.");
		}
	}
	
	public static int insert(Booking booking, Customer customer) {
		int x = 0;
		try {
			x = BookingGateway.insert(booking, customer);
		} catch (BookingGatewayException e) {
			System.out.println("Eroare la insert booking.");
		}
		return x;
	}
}
