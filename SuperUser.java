package com.lcpt2.adam;

public class SuperUser extends User {

	public SuperUser (String username, String password, String name) {
		this.setUsername(username); 
		this.setPassword(password); 
		this.setName(name);            
		this.role = "super_user";  
	}
}
