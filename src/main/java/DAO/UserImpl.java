package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import logging.MyLogger;
import modelProject0.User;
import service.ExecuteApp;
import util.JDBCConnection;

public class UserImpl implements UserDAO{
	
	public static Connection conn = JDBCConnection.getConnection();

	public User getUser(String username) {
		try {
			String sql = "SELECT * FROM users WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, (username));
			
			ResultSet rs = ps.executeQuery();		
			
			if(rs.next()) {
				return (new User(
						rs.getInt("U_ID"),
						rs.getString("USERNAME"),
						rs.getString("PASSWORD"),
						rs.getString("ROLE")));
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<User> getUsers() {
		String sql = "SELECT * FROM users";
		
		List<User> users = new ArrayList<User>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				users.add(new User(
						rs.getInt("U_ID"),
						rs.getString("USERNAME"),
						rs.getString("PASSWORD"),
						rs.getString("ROLE")));
			}		
			return users;	
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	
	public boolean addUser(User user) {
		
		String sql = "CALL add_user(?,?)";
		
		try {
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, user.getUsername());
			cs.setString(2, user.getPassword());
			
			cs.execute();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	//scanner.close();

}
