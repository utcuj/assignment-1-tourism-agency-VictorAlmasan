package presentation;

import java.io.IOException;

import db.UserGateway;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mapper.UserMapper;
import javafx.scene.control.Alert.AlertType;
import model.User;
import model.UserType;
import reporting.AgentReports;

public class AdminLayoutController {

	private MainApp mainApp;
	
	@FXML
	private TableView<User> usersTable;
	@FXML
	private TableColumn<User, String> usernameColumn;
	@FXML
	private TableColumn<User, String> passwordColumn;
	
	@FXML
	private Label userIdLabel;
	@FXML
	private Label usernameLabel;
	@FXML
	private Label passwordLabel;
	@FXML
	private Label userTypeCodeLabel;
	@FXML
	private Label userDescriptionLabel;
	
	@FXML
	private TextField usernameCreateTextField;
	@FXML
	private TextField passwordCreateTextField;
	
	@FXML
	private Label userIdEditLabel;
	@FXML
	private Label userTypeCodeEditLabel;
	@FXML
	private Label userDescriptionEditLabel;
	
	@FXML
	private TextField usernameEditTextField;
	@FXML
	private TextField passwordEditTextField;
	
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
	}
	
	@FXML
	private void initialize() {

		usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
		passwordColumn.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
		
		usernameCreateTextField.setText("");
		passwordCreateTextField.setText("");
		
		showUserInformation(null);


		usersTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showUserInformation(newValue));
	}
	
	
	private void showUserInformation(User user) {
		if (user != null) {
			userIdLabel.setText(Integer.toString(user.getUserId()));
			usernameLabel.setText(user.getUsername());
			passwordLabel.setText(user.getPassword());
			userTypeCodeLabel.setText(user.getUserType().getUserTypeCode());
			userDescriptionLabel.setText(user.getUserType().getUserType());
			
			userIdEditLabel.setText(Integer.toString(user.getUserId()));
			usernameEditTextField.setText(user.getUsername());
			passwordEditTextField.setText(user.getPassword());
			userTypeCodeEditLabel.setText(user.getUserType().getUserTypeCode());
			userDescriptionEditLabel.setText(user.getUserType().getUserType());
			
		} else {
			userIdLabel.setText("");
			usernameLabel.setText("");
			passwordLabel.setText("");
			userTypeCodeLabel.setText("");
			userDescriptionLabel.setText("");
			
			userIdEditLabel.setText("");
			usernameEditTextField.setText("");
			passwordEditTextField.setText("");
			userTypeCodeEditLabel.setText("");
			userDescriptionEditLabel.setText("");
		}
	}
	
	
	@FXML
	public void loadAgents(ActionEvent event){
		try {
			usersTable.setItems(UserMapper.getUserList());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void handleNewUser() {
		try {
			if (!usernameCreateTextField.getText().isEmpty() && !passwordCreateTextField.getText().isEmpty()) {
				int id = UserMapper.insert(new User(0, new UserType("U", "User"),usernameCreateTextField.getText(), 
						passwordCreateTextField.getText()) );
				usersTable.setItems(UserMapper.getUserList());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void handleEditUser() {
		try {
			if (!userIdEditLabel.getText().isEmpty() && !usernameEditTextField.getText().isEmpty() &&
				!passwordEditTextField.getText().isEmpty() && !userTypeCodeEditLabel.getText().isEmpty() &&
				!userDescriptionEditLabel.getText().isEmpty()) {
				UserMapper.edit(new User(Integer.valueOf(userIdEditLabel.getText()), 
						                  new UserType(userTypeCodeEditLabel.getText(),userDescriptionEditLabel.getText()),
						                  usernameEditTextField.getText(),
						                  passwordEditTextField.getText()));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void handleDeleteUser() {
		try {
			if (!userIdLabel.getText().isEmpty() && !usernameLabel.getText().isEmpty() &&
				!passwordLabel.getText().isEmpty() && !userTypeCodeLabel.getText().isEmpty() &&
				!userDescriptionLabel.getText().isEmpty()) {
				UserMapper.delete(new User(Integer.valueOf(userIdLabel.getText()), null, null, null));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void handleReports() {
		try {
			AgentReports.reports();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
