package com.lcpt2.adam;

import java.util.Scanner;
import java.io.IOException;
public class LoginConsole {
	
	private static final int MAX_ATTEMPTS = 5;
	public static UserService userService = new UserService();
	static Scanner input = new Scanner(System.in);
	static String username;
	static String password;
	static String name; 
	
	
	public static void main (String[] args) throws IOException {
		
		int attempts = 1;
		int choice = 0;
		userService.readUsers("users.txt");                  
		userService.writeUpdates("users.txt");	
		
			User personLoggingIn = null;
			while (personLoggingIn == null) {
				System.out.println("Enter Username: ");
				username = input.nextLine();
				System.out.println("Enter Password: ");
				password = input.nextLine();
				
				
				personLoggingIn = userService.userFound(username, password);
				if (personLoggingIn == null && attempts < MAX_ATTEMPTS) {
					System.out.println("Invalid entry, please try again!");
					attempts++;
				} else if(attempts >= MAX_ATTEMPTS) {					
					System.out.println("Too many failed attempts, you are now locked out!");
				break;
			}
			
		}
			
			if (personLoggingIn != null) {
				while(personLoggingIn != null) {
					System.out.println("Welcome: " + personLoggingIn.getName());		
					System.out.println("Please select an option below:");
					choice = selectOption(personLoggingIn);
					
						if (choice == 0 ) {		
							System.out.println("Please enter username of alternate user");
						    String changeUser = askChangeUser();
						    User altUser = userService.selectAltUser(changeUser);
						      personLoggingIn = altUser;					   
						}
						if (choice == 1) {
							System.out.println("Enter new Username: ");
							username = input.nextLine();
							personLoggingIn.setUsername(username);
							userService.writeUpdates(username);
							System.out.println("Username Successfully Updated!");
							System.out.println("    ");
						}
				        
						if (choice == 2) {
							System.out.println("Enter new Password: ");
							password = input.nextLine();
							personLoggingIn.setPassword(password);
							userService.writeUpdates(password);
							System.out.println("Password Successfully Updated!");
							System.out.println("    ");
						}
						
						if (choice == 3) {
							System.out.println("Enter new Name: ");
							name = input.nextLine();
							personLoggingIn.setName(name);
							userService.writeUpdates(name);
							System.out.println("Name Successfully Updated!");
							System.out.println("            ");
						}
						
						if(choice == 4) {
							System.out.print("You have been logged out!");
							break;
						} 
					
					}	
			}
		} 
	
	    static int selectOption(User personLoggingIn) {
		if (personLoggingIn instanceof SuperUser) {
					System.out.println("(0) Log in as another user");
					System.out.println("(1) Update Username");		
					System.out.println("(2) Update Password");		
					System.out.println("(3) Update Name");		
					System.out.println("(4) Exit");	
				    String choice = input.nextLine();
				    return Integer.parseInt(choice);
				    
			}   
			else {
			
				System.out.println("(1) Update Username");		
				System.out.println("(2) Update Password");		
				System.out.println("(3) Update Name");		
				System.out.println("(4) Exit");	
				String choice = input.nextLine();
				return Integer.parseInt(choice);	
			}
		}
	    private static String askChangeUser() {
	    	String changeUser = input.nextLine();
	    	return changeUser;
	    }
}