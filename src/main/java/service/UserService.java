package service;

import java.util.List;
import java.util.Scanner;

import DAO.UserDAO;
import DAO.UserImpl;
import logging.MyLogger;
import modelProject0.User;

public class UserService {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static  int userId;
	
	public static UserDAO userList = new UserImpl();
	
	
	public static boolean addUser(User user) {
		return userList.addUser(user);
	}

	public static User getUser(String username) {
		return userList.getUser(username);
	}
	public static List<User> getUsers() {
		return userList.getUsers();
	}
	
	

	public static boolean login() { 
					
		System.out.println("Enter Username : ");	    
		String writeUsername = scanner.nextLine();    
				
	    System.out.println("Enter Password : ");
	    String writePassword = scanner.nextLine(); 
	    
	    User getUserLoged = getUser(writeUsername);
	    
	    if((writeUsername.isEmpty()) || (writePassword.isEmpty())) {
	    	MyLogger.logger.warn("Not allowed empty inputs!!");
	    	System.out.println("Not allowed empty inputs!!\n");
	    	login();
	    }

        if (getUserLoged == null) {
        	
            System.out.print("This user does not exist.\n\n"); 
            login();
        } else if((getUserLoged.getRole().equals("customer")) && 
	       (getUserLoged.getPassword().equals(writePassword)) &&
	       (getUserLoged.getUsername().equals(writeUsername))){
	    	
	    	System.out.println("\nWelcome "  + writeUsername + " !\n");
	    	userId =  getUserLoged.getU_id();
	    	
	    	ExecuteApp.customerMenu();
	    	
	    } else if((getUserLoged.getRole().equals("employee")) &&
	              (getUserLoged.getPassword().equals(writePassword)) &&
	              (getUserLoged.getUsername().equals(writeUsername))){
	    	System.out.println("\nWelcome "  + writeUsername + " !");
	    	userId =  getUserLoged.getU_id();
	    	
	    	ExecuteApp.employeePage();

	    }  else if(!(getUserLoged.getPassword().equals(writePassword))) {		  
	    	System.out.println("Invalid Password or Username! Try one more time!\n");
	    	login();
	    	return false;
	    }
		return true;
	}


	public static boolean register() {
		
		System.out.println("Create a Username : ");
	    String newUsername = scanner.nextLine(); 
		
	    System.out.println("Create a Password : ");
	    String newPassword = scanner.nextLine();
	    
	    User newUser = new User(newUsername, newPassword, "customer");
	    userId = newUser.getU_id();
	    
	    if((newUsername.isEmpty()) && (newPassword.isEmpty())) {
	    	System.out.println("Not allowed empty inputs!!\n");
	    	register();
	    }
	    
	   	     
	    List<User> users = getUsers();
	    for(User user : users) {
	    	
	    	if(user.getUsername().equals(newUsername)) {
	    		System.out.println(newUsername + " username already exists. Try one more time.\n");
	    		register();
	    		return false;
			} else {
				
	    		System.out.println("You are successfully registered " + newUsername + "!\n");
	    		System.out.println("Please login to have acces to the car lot.\n");
	    		addUser(newUser);
	    	    		
	    		login();
	    	}
		    }
 
		return true;
	}
}
