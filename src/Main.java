import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static String[] splitInput(String input) {
		String returnString[];
		returnString = input.split("	");
		return returnString;
	}
	
	public static void displayFileContent(String role) {
		GetFileContents readFromFile = new GetFileContents();
		if (role.equalsIgnoreCase("zookeeper")) {
			readFromFile.PrintFileContents("zookeeper.txt");
		}
		else if (role.equalsIgnoreCase("admin")) {
			readFromFile.PrintFileContents("zookeeper.txt");
		}	
		else if (role.equalsIgnoreCase("veterinarian")) {
			readFromFile.PrintFileContents("zookeeper.txt");
		}
	}

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		
		//Array to store all the employees
		List<Employee> employeeList = new ArrayList<>();
		int index = 0; //Index of the employee in the list
		boolean isLoggedIn = false;

		// Initialize scanners to gather input:
		Scanner scanner;
		Scanner inputScan = new Scanner(System.in);

		// Initialize useful global variables:
		int loginAttempts = 0;
		String verifyPass;
		String userName;
		String userPass;

		MD5Hash hashPassword = new MD5Hash();

		// Gather each employees credential string:
		FileInputStream employeeCredString;

		employeeCredString = new FileInputStream("credential_file.txt");
		

		BufferedReader br = new BufferedReader(new InputStreamReader(employeeCredString));

		String tempLine = null;
		while ((tempLine = br.readLine()) != null) {
			//Loop through all of the lines in the credential_file
			//System.out.println(tempLine);
			
			//Split the line and construct new employee to add to the this
			String temp[] = splitInput(tempLine);
			Employee newEmployee = new Employee();
			newEmployee.name = temp[0];
			newEmployee.role = temp[3];
			newEmployee.hashedpassword = temp[1];
			employeeList.add(newEmployee);
		}

		while (loginAttempts <= 2 && !isLoggedIn) {
			System.out.println("Please enter your username or enter 'q' to quit: ");
			userName = inputScan.nextLine();
			System.out.println("Please enter your password or enter 'q' to quit: ");
			userPass = inputScan.nextLine();
			if (userName.equalsIgnoreCase("q") || userPass.equalsIgnoreCase("q")) {
				System.out.println("Thank you for using the Authentication Program, have a nice day!");
				break;
			}
			verifyPass = hashPassword.convertToMD5Hash(userPass);
			
			//Iterate through the list to look whether the enter user and password are correct
			for (Employee current : employeeList) {
				if (userName.equalsIgnoreCase(current.name) && verifyPass.equalsIgnoreCase(current.hashedpassword)) {
					//Save the index of found employee
					index = employeeList.indexOf(current);
					isLoggedIn = true;
				}
				else {
					isLoggedIn = false; // Not found
				}
			}
			
			if (isLoggedIn) {
				displayFileContent(employeeList.get(index).role);
				System.out.println("Do you want to log out?");
				String input = inputScan.nextLine();
				if (input.equalsIgnoreCase("yes")) {
					
					loginAttempts = 0;
					isLoggedIn = false;
					continue;
				}	
			}
			else {
				if (loginAttempts < 2) {
					System.out.println("Incorrect username and/or password combination. Please try again.\n");
					loginAttempts++;
					continue;
				} else {
					System.out.println("Incorrect username and/or password combination. "
							+ "You have exceeded the maximum amount of login attempts. Please try again later.");
					break;
				}
			}
		}
	}

}
