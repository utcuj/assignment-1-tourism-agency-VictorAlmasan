package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.CustomerGatewayException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

public class CustomerGateway {
	
	private static final String insertStatementString = "INSERT INTO customers (name, cnp, address) VALUES (?,?,?);";
	//private final static String findStatementStringNume = "SELECT * FROM utilizator where nume = ?";
	private final static String findStatementString = "SELECT * FROM customers ORDER BY name;";
	private final static String editStatementString = "UPDATE customers SET name = ?, cnp = ?, address = ? WHERE customer_id = ?;";
	private final static String deleteStatementString = "DELETE FROM customers WHERE customer_id = ?;";
	
	public static int insert(Customer customer)  throws CustomerGatewayException {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, customer.getName());
			insertStatement.setString(2, customer.getCnp());
			insertStatement.setString(3, customer.getAddress());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new CustomerGatewayException("Customer: insert ", e);
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	public static void edit(Customer customer) throws CustomerGatewayException {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(editStatementString);
			findStatement.setString(1, customer.getName());
			findStatement.setString(2, customer.getCnp());
			findStatement.setString(3, customer.getAddress());
			findStatement.setInt(4, customer.getCustomerId());
			findStatement.executeUpdate();
		} catch (SQLException e) {
			throw new CustomerGatewayException("Customer: edit ", e);
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}

	}
	
	
	public static void delete(Customer customer) throws CustomerGatewayException {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(deleteStatementString);
			findStatement.setInt(1, customer.getCustomerId());
			findStatement.executeUpdate();
		} catch (SQLException e) {
			throw new CustomerGatewayException("Customer: delete ", e);
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}

	}
	
	
	
	public static ObservableList<Customer> getCustomerList() throws CustomerGatewayException {
		
		ObservableList<Customer> customersData = FXCollections.observableArrayList();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			PreparedStatement st = dbConnection.prepareStatement(findStatementString); 
			rs = st.executeQuery();
			while (rs.next()) {
			Customer customer = new Customer(rs.getInt("customer_id"), rs.getString("name"), rs.getString("cnp"), rs.getString("address"));
			customersData.add(customer);
			}
		} catch(SQLException e) {
			throw new CustomerGatewayException("Customer: delete ", e);
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return customersData;
	}
}
