package model;

import java.time.LocalDate;

public class User {
	
	private String id;
	private String name;
	private LocalDate birthday;
	private String password;
	private String role;
	
	
	//Constructor
	public User(String id, String name, LocalDate birthday, String password) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.password = password;
		this.role = "employee";
	}
	
	//Constructor overloading
	public User(String id, String name, String password, String role) {
		this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

	


	//Getters
	public String getId() {
		return id;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	
	public LocalDate getBirthday() {
		return birthday;
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
	
}
