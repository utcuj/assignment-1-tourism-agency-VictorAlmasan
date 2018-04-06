package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UserType {
	
	private StringProperty userTypeCode;
	private StringProperty userType;
	
	public UserType() {
		this(null, null);
	}
	
	public UserType(String userTypeCode, String userType) {
		this.userTypeCode = new SimpleStringProperty(userTypeCode);
		this.userType = new SimpleStringProperty(userType);
	}

	public String getUserTypeCode() {
		return userTypeCode.get();
	}

	public void setUserTypeCode(String userTypeCode) {
		this.userTypeCode.set(userTypeCode);
	}
	
	public StringProperty userTypeCodeProperty() {
		return userTypeCode;
	}

	public String getUserType() {
		return userType.get();
	}

	public void setUserType(String userType) {
		this.userType.set(userType);;
	}
	
	public StringProperty userTypeProperty() {
		return userType;
	}
	
}
