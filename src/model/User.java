package model;

public class User {
	
	private String id;
	private String name;
	private String birthday;
	private String password;
	private String role;
	
	
	//Constructor
	public User(String id, String name, String birthday, String password, String role) {
		this.name = name;
		this.id = id;
		this.birthday = birthday;
		this.password = password;
		this.role = role;
	}
	
	//Constructor overloading
	public User(String id, String name, String password, String role) {
		this.name = name;
		this.id = id;
		this.password = password;
		this.role = role;
	}

	
	//Getters
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getPassword() {
		return password;
	}
	
	public String getRole() {
		return role;
	}
	
}
