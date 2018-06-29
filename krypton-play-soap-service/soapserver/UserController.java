package soapserver;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface UserController {
	static ArrayList<UserModel> users = new ArrayList<UserModel>();
	
	@WebMethod
	boolean addUser(UserModel user) throws UserException;
	
	@WebMethod
	UserModel[] getUsers();

	@WebMethod
	UserModel getUser(int id) throws UserException;
	
	@WebMethod
	boolean deleteUser(int id) throws UserException;
	
	@WebMethod
	boolean updateUser(UserModel user) throws UserException;

	int findUserIndex(int id) throws UserException;
}
