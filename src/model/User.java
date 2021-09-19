package model;

public class User {
	
	private String name;
	private String id;
	private String birthday;
	private String password;
	
	
	//Constructor
	public User(String name, String id, String birthday, String password) {
		this.name = name;
		this.id = id;
		this.birthday = birthday;
		this.password = password;
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
	
}
