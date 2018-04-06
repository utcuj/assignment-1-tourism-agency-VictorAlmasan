package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Booking {
	
	private IntegerProperty bookingId;
	private ObjectProperty<BookingOutcome> bookingOutcome;
	private ObjectProperty<Hotel> hotel;
	private ObjectProperty<Customer> mainCustomer;
	private IntegerProperty totalPrice;
	private ObjectProperty<LocalDate> finalPaymentDate;
	private IntegerProperty toPay;
	
	public Booking() {
		this(0, null, null, null, 0, null,0);
	}
	
	public Booking(int bookingId, BookingOutcome bookingOutcome, Hotel hotel, 
			Customer mainCustomer, int totalPrice, LocalDate finalPayemntDate, int toPay) {
		this.bookingId = new SimpleIntegerProperty(bookingId);
		this.bookingOutcome = new SimpleObjectProperty<BookingOutcome>(bookingOutcome);
		this.hotel = new SimpleObjectProperty<Hotel>(hotel);
		this.mainCustomer = new SimpleObjectProperty<Customer>(mainCustomer);
		this.totalPrice = new SimpleIntegerProperty(totalPrice);
		this.finalPaymentDate = new SimpleObjectProperty<LocalDate>(finalPayemntDate);
		this.toPay = new SimpleIntegerProperty(toPay);
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
	
	public BookingOutcome getBookingOutcome() {
		return bookingOutcome.get();
	}

	public void setBookingOutcome(BookingOutcome bookingOutcome) {
		this.bookingOutcome.set(bookingOutcome);
	}

	public ObjectProperty<BookingOutcome> bookingOutcomeProperty(){
		return bookingOutcome;
	}
	
	public Hotel getHotel() {
		return hotel.get();
	}

	public void setHotel(Hotel hotel) {
		this.hotel.set(hotel);
	}

	public ObjectProperty<Hotel> hotelProperty(){
		return hotel;
	}
	
	public Customer getMainCustomer() {
		return mainCustomer.get();
	}

	public void setMainCustomer(Customer mainCustomer) {
		this.mainCustomer.set(mainCustomer);
	}

	public ObjectProperty<Customer> mainCustomerProperty(){
		return mainCustomer;
	}
	
	public int getTotalPrice() {
		return totalPrice.get();
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice.set(totalPrice);
	}

	public IntegerProperty totalPriceProperty() {
		return totalPrice;
	}
	
	public LocalDate getFinalPaymentDate() {
		return finalPaymentDate.get();
	}

	public void setFinalPaymentDate(LocalDate finalPaymentDate) {
		this.finalPaymentDate.set(finalPaymentDate);
	}

	public ObjectProperty<LocalDate> finalPaymentDateProperty(){
		return finalPaymentDate;
	}
	
	public int getToPay() {
		return toPay.get();
	}

	public void setToPay(int toPay) {
		this.toPay.set(toPay);
	}

	public IntegerProperty toPayProperty() {
		return toPay;
	}
}
