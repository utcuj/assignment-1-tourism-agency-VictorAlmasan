package presentation;

import java.io.IOException;

import db.UserGateway;
import exception.UserGatewayException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mapper.UserMapper;
import model.User;
import util.UtilData;

public class LoginLayoutController {
	
	private MainApp mainApp;
	
	@FXML
	private TextField username;
	
	@FXML
	private TextField password;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	public void login(ActionEvent event) throws IOException{
		
		User user=null;
		try {
			user = UserMapper.loginUser(username.getText(), password.getText());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid data");
		}
		if (user != null) {
			if (user.getUserType().getUserTypeCode().equals("A")) {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminLayout.fxml"));
	            Parent root1 = (Parent) fxmlLoader.load();
	            
	            UtilData.user = user;
	            AdminLayoutController loginLayoutController = fxmlLoader.getController();
	            
				mainApp.getPrimaryStage().close();
	            Stage stage = new Stage();
	            stage.setScene(new Scene(root1));  
	            stage.show();
			} else if (user.getUserType().getUserTypeCode().equals("U")) {
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserLayout.fxml"));
	            Parent root1 = (Parent) fxmlLoader.load();
	            
	            UtilData.user = user;
	            mainApp.getPrimaryStage().close();
	            Stage stage = new Stage();
	            stage.setScene(new Scene(root1));  
	            stage.show();
				}
		
		} else {
			 Alert alert = new Alert(AlertType.WARNING);
		     //alert.initOwner(mainApp.getPrimaryStage());
		     alert.setTitle("Login error");
		     alert.setHeaderText("Bad Input");
		     alert.setContentText("Please insert a valid username and password.");
		     alert.showAndWait();
		}
		
	}

}
