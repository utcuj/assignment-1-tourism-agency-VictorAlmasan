package mapper;

import db.UserGateway;
import exception.UserGatewayException;
import javafx.collections.ObservableList;
import model.User;

public class UserMapper {
	
	public static User loginUser(String username, String password) {
		User user = null;
		try {
			user = UserGateway.loginUser(username, password);
		} catch (UserGatewayException e) {
			System.out.println("Eroare la logare.");
		}
		
		return user;
	}
	
	
	public static ObservableList<User> getUserList() {
		ObservableList<User> list = null;
		try {
			list = UserGateway.getUserList();
		} catch(UserGatewayException e) {
			System.out.println("Eroare la citirea userilor.");
		}
		return list;
	}
	
	
	public static void delete(User user) {
		try {
			UserGateway.delete(user);
		} catch (UserGatewayException e) {
			System.out.println("Eroare la stergere user.");
		}
	}
	
	
	public static void edit(User user) {
		try {
			UserGateway.edit(user);
		} catch (UserGatewayException e) {
			System.out.println("Eroare la stergere user.");
		}
	}
	
	public static int insert(User user) {
		int x = 0;
		try {
			x = UserGateway.insert(user);
		} catch (UserGatewayException e) {
			System.out.println("Eroare la stergere user.");
		}
		return x;
	}

}
