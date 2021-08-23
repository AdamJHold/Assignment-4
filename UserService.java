package com.lcpt2.adam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class UserService {
	
	public User[] loginUsers = new User[20];
	
	
	public User userFound(String username, String password) {
		
		for (User user : loginUsers) {

			if(user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
					return user;
			}
		}
		return null;
	}
	public User selectAltUser(String username) {
		for (User user : loginUsers) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				return user;
			}
		}
		return null;
		
	}
	
	public String writeBack(User user) {
		return user.getUsername() + ", " + user.getPassword() + ", " + user.getName() + ", " + user.getRole() + "\n" ;
	}
	
	public void readUsers(String fileName) throws IOException {
		fileName = "users.txt";
		BufferedReader reader = null;
		String currLine;
		int line = 0;
		
		try {
			reader = new BufferedReader(new FileReader(fileName));			
			while ((currLine = reader.readLine()) != null) {     
				String[] info = currLine.split(", ");
				if ("normal_user".equals(info[3])) {
					loginUsers[line++] = new NormalUser(info[0], info[1], info[2]);	
				}
				if ("super_user".equals(info[3])) {
					loginUsers[line++] = new SuperUser(info[0], info[1], info[2]);
				}	
			}
			
		}  finally {
			if (reader != null)
				reader.close();
		}
	}
	public void writeUpdates(String fileName) throws IOException {
		fileName = "users.txt";
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(fileName));
			Arrays.sort(loginUsers);
			  
			for (User user : loginUsers) {
					writer.write(writeBack(user));
					writer.flush();
		    }
		    
		} finally {
			if (writer != null)
				writer.close();
		}
	}	
}	