import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {

        // Initialize scanners to gather input:
        Scanner scanner;
        Scanner inputScan = new Scanner(System.in);

        // Initialize useful global variables:
        int loginAttempts = 0;
        String verifyPass;
        String userName;
        String userPass;
        String logOut = "";

        // Instantiate new class objects
        GetFileContents readFromFile = new GetFileContents();
        MD5Hash hashPassword = new MD5Hash();

        // Gather each employees credential string:
        FileInputStream employeeCredString;
        String griffin;
        String rosario;
        String bernie;
        String donald;
        String jerome;
        String bruce;

        employeeCredString = new FileInputStream("credential_file.txt");
        scanner = new Scanner(employeeCredString);
        griffin = scanner.nextLine();
        rosario = scanner.nextLine();
        bernie = scanner.nextLine();
        donald = scanner.nextLine();
        jerome = scanner.nextLine();
        bruce = scanner.nextLine();

        employeeCredString.close();

        while (loginAttempts <= 2) {
            System.out.println("Please enter your username or enter 'q' to quit: ");
            userName = inputScan.nextLine();
            System.out.println("Please enter your password or enter 'q' to quit: ");
            userPass = inputScan.nextLine();
            if (userName.equalsIgnoreCase("q") || userPass.equalsIgnoreCase("q")) {
                System.out.println("Thank you for using the Authentication Program, have a nice day!");
                break;
            }
            verifyPass = hashPassword.convertToMD5Hash(userPass);
            if (readFromFile.ReadFileContents("credential_file.txt").contains(verifyPass) && readFromFile.ReadFileContents("credential_file.txt").contains(userName)) {
                if (griffin.contains(verifyPass) && griffin.contains(userName)) {
                    readFromFile.PrintFileContents("zookeeper.txt");
                } else if (rosario.contains(verifyPass) && rosario.contains(userName)) {
                    readFromFile.PrintFileContents("admin.txt");
                } else if (bernie.contains(verifyPass) && bernie.contains(userName)) {
                    readFromFile.PrintFileContents("veterinarian.txt");
                } else if (donald.contains(verifyPass) && donald.contains(userName)) {
                    readFromFile.PrintFileContents("zookeeper.txt");
                } else if (jerome.contains(verifyPass) && jerome.contains(userName)) {
                    readFromFile.PrintFileContents("veterinarian.txt");
                } else if (bruce.contains(verifyPass) && bruce.contains(userName)) {
                    readFromFile.PrintFileContents("admin.txt");
                } else {
                    if (loginAttempts < 2) {
                        System.out.println("Incorrect username and/or password combination. Please try again.\n");
                        loginAttempts++;
                        continue;
                    } else {
                        System.out.println("Incorrect username and/or password combination. " +
                                "You have exceeded the maximum amount of login attempts. Please try again later.");
                        break;
                    }
                }
            } else if (loginAttempts < 2) {
                System.out.println("Incorrect username and/or password combination. Please try again.\n");
                loginAttempts++;
                continue;
            } else {
                System.out.println("Incorrect username and/or password combination. " +
                        "You have exceeded the maximum amount of login attempts. Please try again later.");
                break;
            }

            System.out.println("\nYou will remain logged in until you enter \"quit\" to logout: ");
            logOut = inputScan.nextLine();
            while (!logOut.equalsIgnoreCase("quit")) {
                System.out.println("Enter \"quit\" (without the \" \"s to quit)");
                logOut = inputScan.nextLine();
            }
            if (logOut.equalsIgnoreCase("quit")) {
                System.out.println("You have successfully logged out. Have a nice day!");
                break;
            }
        }
    }
}
