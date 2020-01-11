package DAO;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import modelProject0.User;

class UserDAOTest {

	UserDAO testUser = new UserImpl();
	
	
	@Test
	public void addANewUser() {	
		UserDAO testUser = new UserImpl();
		User user = new User("Boss", "Bossino", "employee");
		Assert.assertTrue(testUser.addUser(user) );		
	}
	
	
	@Test
	public void getOneUser() {	
		User user = new User("Nicoleta", "1111", "employee");
		Assert.assertEquals(user.getUsername() ,testUser.getUser("Nicoleta").getUsername() );		
	}

}
