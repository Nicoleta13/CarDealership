package modelProject0;

public class User {
	int u_id;
	String username;
	String password;
	String role;

	public User() {
		super();
	}
	
	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public User(int u_id, String username, String password, String role) {
		super();
		this.u_id = u_id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User username = " + username + ", password = " + password + ", role = " + role;
	}
}
