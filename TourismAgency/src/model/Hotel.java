package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hotel {

	private IntegerProperty hotelId;
	private ObjectProperty<HotelRating> hotelRating;
	private StringProperty location;
	private StringProperty name;
	
	public Hotel() {
		this(0, null, null, null);
	}

	public Hotel(int hotelId, HotelRating hotelRating, String location, String name) {
		this.hotelId = new SimpleIntegerProperty(hotelId);
		this.hotelRating = new SimpleObjectProperty<HotelRating>(hotelRating);
		this.location = new SimpleStringProperty(location);
		this.name = new SimpleStringProperty(name);
	}

	public int getHotelId() {
		return hotelId.get();
	}

	public void setHotelId(int hotelId) {
		this.hotelId.set(hotelId);
	}

	public IntegerProperty hotelIdProperty() {
		return hotelId;
	}
	
	public HotelRating getHotelRating() {
		return hotelRating.get();
	}

	public void setHotelRating(HotelRating hotelRating) {
		this.hotelRating.set(hotelRating);
	}

	public ObjectProperty<HotelRating> hotelRatingProperty() {
		return hotelRating;
	}
	
	public String getLocation() {
		return location.get();
	}

	public void setLocation(String location) {
		this.location.set(location);
	}
	
	public StringProperty locationProperty() {
		return location;
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	public StringProperty nameProperty() {
		return name;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
	
	
}
