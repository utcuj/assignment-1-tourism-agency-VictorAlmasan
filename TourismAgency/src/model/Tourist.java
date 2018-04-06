package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Tourist {

	private IntegerProperty touristId;
	private IntegerProperty bookingId;
	private IntegerProperty customerId;
	
	public Tourist() {
		this(0, 0, 0);
	}
	
	public Tourist(int touristId, int bookingId, int customerId) {
		this.touristId = new SimpleIntegerProperty(touristId);
		this.bookingId = new SimpleIntegerProperty(bookingId);
		this.customerId = new SimpleIntegerProperty(customerId);
	}
	
	public int getTouristId() {
		return touristId.get();
	}

	public void setTouristId(int touristId) {
		this.touristId.set(touristId);
	}

	public IntegerProperty touristIdProperty() {
		return touristId;
	}
	
	public int getBookingId() {
		return bookingId.get();
	}

	public void setBookingId(int bookingId) {
		this.bookingId.set(bookingId);
	}

	public IntegerProperty bookingIdProperty() {
		return bookingId;
	}
	
	public int getCustomerId() {
		return customerId.get();
	}

	public void setCustomerId(int customerId) {
		this.customerId.set(customerId);
	}

	public IntegerProperty customerIdProperty() {
		return customerId;
	}
	
}
