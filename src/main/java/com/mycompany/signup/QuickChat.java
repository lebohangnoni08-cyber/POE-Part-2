/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.signup;
import java.util.Scanner;
/**
 *
 * @author Student
 */
import java.util.Scanner;

public class QuickChat {
    private static Scanner scanner = new Scanner(System.in);
    private static MessageManager manager = new MessageManager();
    private static int messagesSentCounter = 0;

    public static void main(String[] args) {
        // --- MOCK LOGIN (Part 1 simulation) ---
        System.out.println("=== System Login ===");
        System.out.print("Enter Username: ");
        String user = scanner.nextLine();
        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();
        
        if(user.isEmpty() || pass.isEmpty()) {
            System.out.println("Login failed. Exiting.");
            return;
        }
        System.out.println("Login successful. Welcome, " + user + "!");

        // Load any existing stored messages from JSON
        manager.loadStoredFromJSON();

        boolean running = true;
        while (running) {
            System.out.println("\n--- QuickChat Menu ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Stored Messages (Part 3)");
            System.out.println("4) Quit");
            System.out.print("Choose option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleSendMessages();
                    break;
                case "2":
                    System.out.println("Coming Soon.");
                    break;
                case "3":
                    handleStoredMessagesMenu();
                    break;
                case "4":
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void handleSendMessages() {
        System.out.print("How many messages do you wish to enter? ");
        int limit = scanner.nextInt();
        scanner.nextLine();

        while (messagesSentCounter < limit) {
            System.out.print("Recipient: ");
            String rec = scanner.nextLine();
            System.out.print("Message: ");
            String msgTxt = scanner.nextLine();

            Message msg = new Message(messagesSentCounter, rec, msgTxt, "Sent"); // Default to sent for simplicity
            
            System.out.println("\n1. Send | 2. Disregard | 3. Store");
            String action = scanner.nextLine();

            if (action.equals("2")) {
                // Create a new object with Disregard flag
                Message disMsg = new Message(messagesSentCounter, rec, msgTxt, "Disregard");
                manager.addMessage(disMsg);
                System.out.println("Press 0 to delete the message");
            } else if (action.equals("3")) {
                Message storeMsg = new Message(messagesSentCounter, rec, msgTxt, "Stored");
                manager.addMessage(storeMsg);
                System.out.println("Message successfully stored");
            } else {
                manager.addMessage(msg);
                System.out.println("Message successfully sent");
            }
            messagesSentCounter++;
        }
    }

    private static void handleStoredMessagesMenu() {
        boolean subMenu = true;
        while (subMenu) {
            System.out.println("\n--- Stored Messages Menu ---");
            System.out.println("a) Display sender and recipient of all stored messages");
            System.out.println("b) Display the longest stored message");
            System.out.println("c) Search for a message ID");
            System.out.println("d) Search messages for a particular recipient");
            System.out.println("e) Delete a message using hash");
            System.out.println("f) Display full report");
            System.out.println("g) Back to Main Menu");
            System.out.print("Choose: ");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "a": manager.displaySenderAndRecipient(); break;
                case "b": System.out.println("Longest: " + manager.getLongestMessage()); break;
                case "c": 
                    System.out.print("Enter ID: ");
                    System.out.println(manager.searchByMessageID(scanner.nextLine())); 
                    break;
                case "d": 
                    System.out.print("Enter Recipient: ");
                    manager.searchByRecipient(scanner.nextLine()); 
                    break;
                case "e": 
                    System.out.print("Enter Hash: ");
                    System.out.println(manager.deleteByHash(scanner.nextLine())); 
                    break;
                case "f": manager.displayReport(); break;
                case "g": subMenu = false; break;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}