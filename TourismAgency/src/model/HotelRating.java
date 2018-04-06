package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HotelRating {

	private StringProperty hotelRating;
	private StringProperty quality;
	
	public HotelRating(){
		this(null, null);
	}

	public HotelRating(String hotelRating, String quality) {
		this.hotelRating = new SimpleStringProperty(hotelRating);
		this.quality = new SimpleStringProperty(quality);
	}

	public String getHotelRatingCode() {
		return hotelRating.get();
	}

	public void setHotelRatingCode(String hotelRatingCode) {
		this.hotelRating.set(hotelRatingCode);
	}

	public StringProperty hotelRatingCodeProperty() {
		return hotelRating;
	}
	
	public String getQuality() {
		return quality.get();
	}

	public void setQuality(String quality) {
		this.quality.set(quality);
	}
	
	public StringProperty qualityProperty() {
		return quality;
	}
	
}
