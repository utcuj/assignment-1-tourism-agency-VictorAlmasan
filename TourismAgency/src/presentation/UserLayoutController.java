package presentation;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mapper.BookingMapper;
import mapper.CustomerMapper;
import mapper.HotelMapper;
import mapper.LoggingMapper;
import mapper.PaymentMapper;
import model.Booking;
import model.BookingOutcome;
import model.Customer;
import model.Hotel;
import model.Payment;
import util.UtilData;

public class UserLayoutController {

	// customer
	@FXML
	private TableView<Customer> customersTable;
	@FXML
	private TableColumn<Customer, String> nameColumn;
	@FXML
	private TableColumn<Customer, String> addressColumn;

	// customer information
	@FXML
	private Label customerIdLabel;
	@FXML
	private Label nameLabel;
	@FXML
	private Label cnpLabel;
	@FXML
	private Label addressLabel;

	// customer edit
	@FXML
	private Label customerIdEditLabel;
	@FXML
	private Label cnpEditLabel;

	@FXML
	private TextField nameEditTextField;
	@FXML
	private TextField addressEditTextField;

	// customer create
	@FXML
	private TextField nameCreateTextField;
	@FXML
	private TextField cnpCreateTextField;
	@FXML
	private TextField addressCreateTextField;

	// booking
	@FXML
	private TableView<Booking> bookingsTable;
	@FXML
	private TableColumn<Booking, Customer> customerNameColumn;
	@FXML
	private TableColumn<Booking, BookingOutcome> bookingOutcomeDescriptionColumn;

	// booking information
	@FXML
	private Label bookingIdLabel;
	@FXML
	private Label bookingOutcomeDescriptionLabel;
	@FXML
	private Label hotelLabel;
	@FXML
	private Label customerLabel;
	@FXML
	private Label totalPriceLabel;
	@FXML
	private Label finalPaymentDateLabel;
	@FXML
	private Label toPayLabel;

	// add payment
	@FXML
	private TextField paymentAmountTextField;
	@FXML
	private Button payButton;
	
	
	// booking edit
	@FXML
	private Label bookingIdEditLabel;
	@FXML
	private Label customerEditLabel;
	@FXML
	private Label hotelEditLabel;
	@FXML
	private TextField totalPriceEditTextField;
	
	// add booking
	@FXML
	private ComboBox<Customer> customerComboBox;
	@FXML
	private ComboBox<Hotel> hotelComboBox;
	@FXML
	private ComboBox<Customer> customerFriendComboBox;
	@FXML
	private TextField totalPriceAddTextField;
	@FXML
	private DatePicker finalPaymentDatePicker;

	@FXML
	private void initialize() {

		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());

		customerNameColumn.setCellValueFactory(cellData -> cellData.getValue().mainCustomerProperty());
		bookingOutcomeDescriptionColumn.setCellValueFactory(cellData -> cellData.getValue().bookingOutcomeProperty());

		nameEditTextField.setText("");
		addressEditTextField.setText("");

		paymentAmountTextField.setDisable(true);
		payButton.setDisable(true);

		showCustomerInformation(null);
		showBookingInformation(null);

		customerComboBox.setItems(CustomerMapper.getCustomerList());
		hotelComboBox.setItems(HotelMapper.getHotelList());
		customerFriendComboBox.setItems(CustomerMapper.getCustomerList());
		
		customersTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showCustomerInformation(newValue));

		bookingsTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showBookingInformation(newValue));
	}

	private void showCustomerInformation(Customer customer) {
		if (customer != null) {
			customerIdLabel.setText(Integer.toString(customer.getCustomerId()));
			nameLabel.setText(customer.getName());
			cnpLabel.setText(customer.getCnp());
			addressLabel.setText(customer.getAddress());

			customerIdEditLabel.setText(Integer.toString(customer.getCustomerId()));
			nameEditTextField.setText(customer.getName());
			cnpEditLabel.setText(customer.getCnp());
			addressEditTextField.setText(customer.getAddress());

		} else {
			customerIdLabel.setText("");
			nameLabel.setText("");
			cnpLabel.setText("");
			addressLabel.setText("");

			customerIdEditLabel.setText("");
			nameEditTextField.setText("");
			cnpEditLabel.setText("");
			addressEditTextField.setText("");
		}
	}

	@FXML
	public void loadCustomers(ActionEvent event) {
		try {
			customersTable.setItems(CustomerMapper.getCustomerList());
			LoggingMapper.insert(UtilData.user.getUserId(), "VC", LocalDate.now());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void handleNewCustomer() {
		try {
			if (!nameCreateTextField.getText().isEmpty() && !cnpCreateTextField.getText().isEmpty()
					&& !addressCreateTextField.getText().isEmpty()) {
				int id = CustomerMapper.insert(new Customer(0, nameCreateTextField.getText(),
						cnpCreateTextField.getText(), addressCreateTextField.getText()));
				LoggingMapper.insert(UtilData.user.getUserId(), "AC", LocalDate.now());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void handleEditCustomer() {
		try {
			if (!customerIdEditLabel.getText().isEmpty() && !nameEditTextField.getText().isEmpty()
					&& !cnpEditLabel.getText().isEmpty() && !addressEditTextField.getText().isEmpty()) {
				CustomerMapper.edit(new Customer(Integer.valueOf(customerIdEditLabel.getText()),
						nameEditTextField.getText(), cnpEditLabel.getText(), addressEditTextField.getText()));
				LoggingMapper.insert(UtilData.user.getUserId(), "UC", LocalDate.now());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showBookingInformation(Booking booking) {
		if (booking != null) {

			bookingIdLabel.setText(Integer.toString(booking.getBookingId()));
			bookingOutcomeDescriptionLabel.setText(booking.getBookingOutcome().getBookingOutcomeDescrption());
			hotelLabel.setText(booking.getHotel().getName());
			customerLabel.setText(booking.getMainCustomer().getName());
			totalPriceLabel.setText(Integer.toString(booking.getTotalPrice()));
			finalPaymentDateLabel.setText(booking.getFinalPaymentDate().toString());
			toPayLabel.setText(Integer.toString(booking.getToPay()));
			
			if (booking.getToPay() > 0 && booking.getFinalPaymentDate().isAfter(LocalDate.now())) {
				paymentAmountTextField.setDisable(false);
				payButton.setDisable(false);
			} else {
				paymentAmountTextField.setDisable(true);
				payButton.setDisable(true);
			}
			
			bookingIdEditLabel.setText(Integer.toString(booking.getBookingId()));
			customerEditLabel.setText(booking.getMainCustomer().getName());
			hotelEditLabel.setText(booking.getHotel().getName());
			totalPriceEditTextField.setText(Integer.toString(booking.getTotalPrice()));
		} else {
			bookingIdLabel.setText("");
			bookingOutcomeDescriptionLabel.setText("");
			hotelLabel.setText("");
			customerLabel.setText("");
			totalPriceLabel.setText("");
			finalPaymentDateLabel.setText("");
			toPayLabel.setText("");
			bookingIdEditLabel.setText("");
			customerEditLabel.setText("");
			hotelEditLabel.setText("");
			totalPriceEditTextField.setText("");
		}
	}

	@FXML
	public void loadBookings() {
		try {
			bookingsTable.setItems(BookingMapper.getBookingList());
			paymentAmountTextField.setDisable(true);
			payButton.setDisable(true);
			LoggingMapper.insert(UtilData.user.getUserId(), "VB", LocalDate.now());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void handleAddPayment() {
		try {
			if (Integer.parseInt(paymentAmountTextField.getText()) < Integer.parseInt(toPayLabel.getText())) {
				PaymentMapper.insert(new Payment(0, Integer.valueOf(bookingIdLabel.getText()),
						Integer.valueOf(paymentAmountTextField.getText()), LocalDate.now()));
				toPayLabel.setText(Integer.toString(
						Integer.valueOf(toPayLabel.getText()) - Integer.valueOf(paymentAmountTextField.getText())));
				LoggingMapper.insert(UtilData.user.getUserId(), "AP", LocalDate.now());
			} else if (Integer.parseInt(paymentAmountTextField.getText()) == Integer.parseInt(toPayLabel.getText())) {
				PaymentMapper.insert(new Payment(0, Integer.valueOf(bookingIdLabel.getText()),
						Integer.valueOf(paymentAmountTextField.getText()), LocalDate.now()));
				BookingMapper.updateBookingOutcomeCode(Integer.valueOf(bookingIdLabel.getText()), "O");
				paymentAmountTextField.setText("");
				paymentAmountTextField.setDisable(true);
				payButton.setDisable(true);
				toPayLabel.setText(Integer.toString(
						Integer.valueOf(toPayLabel.getText()) - Integer.valueOf(paymentAmountTextField.getText())));
				LoggingMapper.insert(UtilData.user.getUserId(), "AP", LocalDate.now());
			} else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Payment Warning");
				alert.setHeaderText("Bad Input");
				alert.setContentText(
						"Please insert a valid amount. The amount should be less or equal than toPay amount.");
				alert.showAndWait();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void handleEditBooking() {
		try {
			if (!bookingIdEditLabel.getText().isEmpty() && !customerEditLabel.getText().isEmpty()
					&& !hotelEditLabel.getText().isEmpty() && !totalPriceEditTextField.getText().isEmpty()
					 && Integer.valueOf(totalPriceEditTextField.getText()).intValue() > 0) {
				BookingMapper.update(Integer.valueOf(bookingIdEditLabel.getText()), Integer.valueOf(totalPriceEditTextField.getText()));
				loadBookings();
				LoggingMapper.insert(UtilData.user.getUserId(), "UB", LocalDate.now());
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void handleAddBooking() {
		try {
			
			if (customerComboBox.getValue()!= null && hotelComboBox.getValue()!= null
					&& customerFriendComboBox.getValue()!= null && !totalPriceAddTextField.getText().isEmpty()
					&& Integer.valueOf(totalPriceAddTextField.getText()).intValue() > 0
					&& finalPaymentDatePicker.getValue().isAfter(LocalDate.now())) {
				BookingOutcome bookingOutcome = new BookingOutcome("P", "In progress");
				BookingMapper.insert(new Booking(0, bookingOutcome, hotelComboBox.getValue(), customerComboBox.getValue(),
									Integer.valueOf(totalPriceAddTextField.getText()), finalPaymentDatePicker.getValue(),
									Integer.valueOf(totalPriceAddTextField.getText())), customerFriendComboBox.getValue());
				loadBookings();
				LoggingMapper.insert(UtilData.user.getUserId(), "AB", LocalDate.now());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
