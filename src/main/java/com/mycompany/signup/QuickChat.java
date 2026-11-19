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
public class QuickChat {
    private static Scanner scanner = new Scanner(System.in);
    private static int totalMessagesToSend = 0;
    private static int messagesSentCounter = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to QuickChat.");

        System.out.print("How many messages do you wish to enter? ");
        while (!scanner.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            scanner.next();
        }
        totalMessagesToSend = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean running = true;

        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("Option 1) Send Messages");
            System.out.println("Option 2) Show recently sent messages");
            System.out.println("Option 3) Quit");
            System.out.print("Choose an option: ");
            
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleSendMessages();
                    break;
                case "2":
                    System.out.println("Coming Soon.");
                    break;
                case "3":
                    System.out.println("Exiting QuickChat. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void handleSendMessages() {
        if (messagesSentCounter >= totalMessagesToSend) {
            System.out.println("You have already sent your set number of messages (" + totalMessagesToSend + ").");
            return;
        }

        while (messagesSentCounter < totalMessagesToSend) {
            System.out.println("\n--- Message " + (messagesSentCounter + 1) + " of " + totalMessagesToSend + " ---");
            
            System.out.print("Enter recipient cell number (e.g., +27718693002): ");
            String recipient = scanner.nextLine();

            String messageText = "";
            boolean validMessage = false;
            while (!validMessage) {
                System.out.print("Enter message (max 250 characters): ");
                messageText = scanner.nextLine();
                
                if (messageText.length() > 250) {
                    System.out.println("Please enter a message of less than 250 characters.");
                } else {
                    validMessage = true;
                }
            }

            Message msg = new Message(messagesSentCounter, recipient, messageText);

            String recipientStatus = msg.checkRecipientCell();
            if (!recipientStatus.equals("Cell phone number successfully captured.")) {
                System.out.println(recipientStatus);
                continue; 
            }

            System.out.println("\nMessage Details:");
            System.out.println(msg.toString());

            System.out.println("\nOptions:");
            System.out.println("1 - Send Message");
            System.out.println("2 - Disregard Message (Press 0 to delete)");
            System.out.println("3 - Store Message to send later");
            System.out.print("Choose an action: ");
            String actionChoice = scanner.nextLine();

            String actionResult = msg.SentMessage(actionChoice);
            System.out.println(actionResult);

            if (actionChoice.equals("1") || actionChoice.equals("3")) {
                messagesSentCounter++;
            } else if (actionChoice.equals("0")) {
                System.out.println("Message deleted.");
            }
        }

        System.out.println("\nAll messages processed.");
        Message temp = new Message(0, "", ""); 
        System.out.println("Total messages successfully sent: " + temp.returnTotalMessagess());
    }
}
    

