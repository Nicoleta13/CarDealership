package DAO;

import java.util.List;

import modelProject0.User;

public interface UserDAO {

	public boolean addUser(User user);
	public User getUser(String username);
	public List<User> getUsers();

}
