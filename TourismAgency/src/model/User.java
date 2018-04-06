package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {

	private IntegerProperty userId;
	private ObjectProperty<UserType> userType;
	private StringProperty username;
	private StringProperty password;
	
	public User() {
		this(0, null, null, null);
	}
	
	public User(int userId, UserType userType, String username, String password) {
		super();
		this.userId = new SimpleIntegerProperty(userId);
		this.userType = new SimpleObjectProperty<UserType>(userType);
		this.username = new SimpleStringProperty(username);
		this.password = new SimpleStringProperty(password);
	}

	public int getUserId() {
		return userId.get();
	}

	public void setUserId(int userId) {
		this.userId.set(userId);
	}

	public IntegerProperty userIdProperty() {
		return userId;
	}
	
	public UserType getUserType() {
		return userType.get();
	}

	public void setUserType(UserType userType) {
		this.userType.set(userType);
	}

	public ObjectProperty<UserType> userTypeProperty(){
		return userType;
	}
	
	public String getUsername() {
		return username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public StringProperty usernameProperty() {
		return username;
	}
	
	public String getPassword() {
		return password.get();
	}

	public void setPassword(String password) {
		this.password.set(password);
	}
	
	public StringProperty passwordProperty() {
		return password;
	}
	
}
