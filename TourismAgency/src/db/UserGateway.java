package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exception.CustomerGatewayException;
import exception.UserGatewayException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.User;
import model.UserType;

public class UserGateway {
	
	private final static String findStatementString = "SELECT u.user_id, u.username, u.password, u.user_type_code, ut.user_type_code , ut.user_type_description"
			+ " FROM users u INNER JOIN user_types ut ON u.user_type_code = ut.user_type_code WHERE u.user_type_code = 'U';";
	
	private static final String insertStatementString = "INSERT INTO users (username, password, user_type_code) VALUES (?,?,?);";
	
	private final static String editStatementString = "UPDATE users SET username = ?, password = ? WHERE user_id = ?;";
	
	private final static String deleteStatementString = "DELETE FROM users WHERE user_id = ?;";
	
	private final static String loginStatementString = "SELECT u.user_id, u.username, u.password, u.user_type_code, ut.user_type_code ,ut.user_type_description" + 
			" FROM users u INNER JOIN user_types ut ON u.user_type_code = ut.user_type_code WHERE u.username = ? AND u.password = ?;";
	
	public static int insert(User user)  throws UserGatewayException {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, user.getUsername());
			insertStatement.setString(2, user.getPassword());
			insertStatement.setString(3, user.getUserType().getUserTypeCode());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new UserGatewayException("User: insert ", e);
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	public static void edit(User user) throws UserGatewayException {

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(editStatementString);
			findStatement.setString(1, user.getUsername());
			findStatement.setString(2, user.getPassword());
			findStatement.setInt(3, user.getUserId());
			findStatement.executeUpdate();
		} catch (SQLException e) {
			throw new UserGatewayException("User: edit ", e);
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}

	}
	
	
	public static void delete(User user) throws UserGatewayException {

		// aici trebuie sters si din logging
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(deleteStatementString);
			findStatement.setInt(1, user.getUserId());
			findStatement.executeUpdate();
		} catch (SQLException e) {
			throw new UserGatewayException("User: delete ", e);
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}

	}
	
	public static ObservableList<User> getUserList() throws UserGatewayException {
		
		ObservableList<User> usersData = FXCollections.observableArrayList();
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			PreparedStatement st = dbConnection.prepareStatement(findStatementString); 
			rs = st.executeQuery();
			while (rs.next()) {
			UserType userType = new UserType(rs.getString("ut.user_type_code"),  rs.getString("ut.user_type_description"));
			User user = new User(rs.getInt("u.user_id"), userType, rs.getString("u.username"), rs.getString("u.password"));
			usersData.add(user);
			}
		} catch(SQLException e) {
			throw new UserGatewayException("User: get ", e);
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return usersData;
	}
	
	public static User loginUser(String username, String password) throws UserGatewayException {
		
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		User user = null;
		UserType userType = null;
		
		try {
			PreparedStatement st = dbConnection.prepareStatement(loginStatementString); 
			st.setString(1, username);
			st.setString(2, password);
			rs = st.executeQuery();
			if (rs.next()) {
				userType = new UserType(rs.getString("ut.user_type_code"), rs.getString("ut.user_type_description"));
				user = new User(rs.getInt("u.user_id"), userType, rs.getString("u.username"), rs.getString("u.password"));
				//System.out.println(user.toString());
			}
		} catch(SQLException e) {
			throw new UserGatewayException("User: login ", e);
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return user;
		
	}

}
