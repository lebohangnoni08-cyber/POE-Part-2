/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.signup;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Student
 */
public class SignUp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("===Welcome to the Registration System===");
        
        System.out.println("Please enter your first name: ");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your last name: ");
        String lastName = scanner.nextLine();
        
        Login loginSystem = new Login(firstName, lastName);
        
        System.out.println("\n--- Register Account ---");
        System.out.println("Please enter your username (must contain '_' and max 5 chars): ");
        String regUsername = scanner.nextLine();
        
        System.out.println("Please enter your password (min 8 chars, 1 capital, 1 number, 1 special): ");
        String regPassword = scanner.nextLine();
        
        System.out.println("Please enter phone number (e.g., +27838968520) :");
        String regCellPhone = scanner.nextLine();
        
        String registrationMessage = loginSystem.registerUser(regUsername, regPassword, regCellPhone);
        System.out.println("\n" + registrationMessage);
        
        if (registrationMessage.contains("successfully")){
            System.out.println("\n--- Login Phase ---");
            System.out.println("Please enter Username: ");
            String loginUsername = scanner.nextLine();
            
            System.out.println("Please enter password: ");
            String loginPassword = scanner.nextLine();
            
            boolean isLoggedIn = loginSystem.loginUser(loginUsername, loginPassword);
            
            System.out.println("\n" + loginSystem.returnLoginStatus(isLoggedIn));
        } else {
            System.out.println("\n Registration failed. Please restart the application and try again.");
        }
        
        scanner.close();   
   
    System.out.println("\n--- Login Phase ---");
        loginSystem = new Login("Lebohang", "Noni");
        boolean isLoggedIn = true; 
        
        if (isLoggedIn) {
            System.out.println("\nWelcome to QuickChat.");
            
            System.out.print("How many messages do you wish to enter? ");
            int numMessages = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            List<Message> sentMessages = new ArrayList<>();
            int messageCounter = 0;
            
            boolean running = true;
            while (running) {
                System.out.println("\n--- QuickChat Menu ---");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");
                int menuChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (menuChoice) {
                    case 1:
                        if (messageCounter >= numMessages) {
                            System.out.println("You have reached your message limit.");
                            break;
                        }
                        
                        System.out.print("Enter recipient cell number (e.g., +27718693002): ");
                        String recipient = scanner.nextLine();
                        
                        System.out.print("Enter message (max 250 chars): ");
                        String text = scanner.nextLine();
                        
                        Message msg = new Message(recipient, text, messageCounter);
                        
                        // Validate length
                        String lengthCheck = msg.checkMessageLength();
                        System.out.println(lengthCheck);
                        if (!lengthCheck.equals("Message ready to send.")) {
                            break; // Skip if too long
                        }
                        
                        // Validate cell
                        String cellCheck = msg.checkRecipientCell();
                        System.out.println(cellCheck);
                        if (!cellCheck.equals("Cell phone number successfully captured.")) {
                            break;
                        }
                        
                        // Ask to Send, Discard, Store
                        System.out.println("\nWhat would you like to do?");
                        System.out.println("1) Send Message");
                        System.out.println("2) Disregard Message");
                        System.out.println("3) Store Message to send later");
                        int action = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        
                        String actionResult = msg.sentMessage(action);
                        System.out.println(actionResult);
                        
                        if (action == 1) {
                            sentMessages.add(msg);
                            System.out.println("\n--- Message Details ---");
                            System.out.println(msg.printMessages());
                            messageCounter++;
                        } else if (action == 3) {
                            messageCounter++;
                        }
                        break;
                        
                    case 2:
                        System.out.println("Coming Soon.");
                        break;
                        
                    case 3:
                        System.out.println("Total messages sent: " + messageCounter);
                        System.out.println("Exiting QuickChat...");
                        running = false;
                        break;
                        
                    default:
                        System.out.println("Invalid option. Please choose 1, 2, or 3.");
                }
            }
        } else {
            System.out.println("Login failed. Cannot access QuickChat.");
        }
        
        scanner.close();
    }
}

