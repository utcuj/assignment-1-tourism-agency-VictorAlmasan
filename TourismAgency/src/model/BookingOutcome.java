package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookingOutcome {

	private StringProperty bookingOutcomeCode;
	private StringProperty bookingOutcomeDescription;
	
	public BookingOutcome() {
		this(null, null);
	}

	public BookingOutcome(String bookingOutcomeCode, String bookingOutcomeDescription) {
		super();
		this.bookingOutcomeCode = new SimpleStringProperty(bookingOutcomeCode);
		this.bookingOutcomeDescription =new SimpleStringProperty(bookingOutcomeDescription);
	}

	public String getBookingOutcomeCode() {
		return bookingOutcomeCode.get();
	}

	public void setBookingOutcomeCode(String bookingOutcomeCode) {
		this.bookingOutcomeCode.set(bookingOutcomeCode);
	}

	public StringProperty bookingOutcomeCodeProperty() {
		return bookingOutcomeCode;
	}
	

	public String getBookingOutcomeDescrption() {
		return bookingOutcomeDescription.get();
	}

	public void setBookingOutcomeDescription(String bookingOutcomeDescription) {
		this.bookingOutcomeDescription.set(bookingOutcomeDescription);
	}

	public StringProperty bookingOutcomeDescriptionProperty() {
		return bookingOutcomeDescription;
	}

	@Override
	public String toString() {
		return getBookingOutcomeDescrption();
	}
	
	
	
}
