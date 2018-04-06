package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {

	private IntegerProperty customerId;
	private StringProperty name;
	private StringProperty cnp;
	private StringProperty address;
	
	public Customer() {
		this(0, null, null, null);
	}

	public Customer(int customerId, String name, String cnp, String address) {
		super();
		this.customerId = new SimpleIntegerProperty(customerId);
		this.name = new SimpleStringProperty(name);
		this.cnp = new SimpleStringProperty(cnp);
		this.address = new SimpleStringProperty(address);
	}

	public int getCustomerId() {
		return customerId.get();
	}

	public void setCustomerId(int customerId) {
		this.customerId.set(customerId);
	}

	public IntegerProperty customerIdProperty() {
		return customerId;
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
	
	public String getCnp() {
		return cnp.get();
	}

	public void setCnp(String cnp) {
		this.cnp.set(cnp);
	}

	public StringProperty cnpProperty() {
		return cnp;
	}
	
	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address.set(address);
	}
	
	public StringProperty addressProperty() {
		return address;
	}

	@Override
	public String toString() {
		return getName();
	}
	
	
}
