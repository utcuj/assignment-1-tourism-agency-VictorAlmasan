package model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Payment {
	
	private IntegerProperty paymentId;
	private IntegerProperty bookingId;
	private IntegerProperty paymentAmount;
	private ObjectProperty<LocalDate> paymentDate;

	public Payment() {
		this(0, 0, 0, null);
	}
	
	public Payment(int paymentId, int bookingId, int paymentAmount, LocalDate paymentDate) {
		this.paymentId = new SimpleIntegerProperty(paymentId);
		this.bookingId = new SimpleIntegerProperty(bookingId);
		this.paymentAmount = new SimpleIntegerProperty(paymentAmount);
		this.paymentDate = new SimpleObjectProperty<LocalDate>(paymentDate);
	}
	
	public int getPaymentId() {
		return paymentId.get();
	}

	public void setPaymentId(int paymentId) {
		this.paymentId.set(paymentId);
	}

	public IntegerProperty paymentIdProperty() {
		return paymentId;
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
	
	public int getPaymentAmount() {
		return paymentAmount.get();
	}

	public void setPaymentAmount(int paymentAmount) {
		this.paymentAmount.set(paymentAmount);
	}

	public IntegerProperty paymentAmountProperty() {
		return paymentAmount;
	}
	
	public LocalDate getPaymentDate() {
		return paymentDate.get();
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate.set(paymentDate);
	}

	public ObjectProperty<LocalDate> paymentDateProperty(){
		return paymentDate;
	}
	
}
 	