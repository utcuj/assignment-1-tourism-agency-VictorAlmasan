package mapper;

import db.CustomerGateway;
import exception.CustomerGatewayException;
import javafx.collections.ObservableList;
import model.Customer;

public class CustomerMapper {

	
	public static int insert(Customer customer) {
		int x = 0;
		try {
			x = CustomerGateway.insert(customer);
		} catch (CustomerGatewayException e) {
			System.out.println("Eroare la customer insert.");
		}
		return x;
	}
	
	
	public static void edit(Customer customer) {
		try {
			 CustomerGateway.edit(customer);
		} catch (CustomerGatewayException e) {
			System.out.println("Eroare la customer insert.");
		}
	}
	
	
	public static ObservableList<Customer> getCustomerList() {
		ObservableList<Customer> list = null;
		try {
			 list = CustomerGateway.getCustomerList();
		} catch (CustomerGatewayException e) {
			System.out.println("Eroare la customer insert.");
		}
		return list;
	}
	
}
