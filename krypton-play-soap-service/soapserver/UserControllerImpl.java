package soapserver;

import java.util.ArrayList;
import javax.jws.WebService;

@WebService(endpointInterface = "soapserver.UserController")
public class UserControllerImpl implements UserController {
	
	private static ArrayList<UserModel> users = new ArrayList<UserModel>();
	private static int maxUserCount = 100;
	
	public boolean addUser(UserModel user) throws UserException {
		try {
			findUserIndex(user.getId());
		} catch (UserException e) {
			while(users.size() > maxUserCount)
				users.remove(0);
			
			users.add(user);
			return true;
		}
		
		throw new UserException("user alredy exist with id: " + user.getId());
	}
	
	public UserModel[] getUsers(){
		UserModel[] u = users.stream().toArray(UserModel[]::new);
		return u;
	}

	public UserModel getUser(int id) throws UserException{
		int index = findUserIndex(id);
		return users.get(index);
	}
	
	public boolean deleteUser(int id) throws UserException{
		int index = findUserIndex(id);
		
		users.remove(index);
		
		return true;
	}
	
	public boolean updateUser(UserModel user) throws UserException {
		int index = findUserIndex(user.getId());
		
		users.remove(index);
		users.add(user);
		
		return true;
	}

	public int findUserIndex(int id) throws UserException {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == id)
				return i;
		}

		throw new UserException("user with id: " + id + " not found in the users list");
	}
}

class UserException extends Exception {
	public UserException(String message) {
		super(message);
	}
}
