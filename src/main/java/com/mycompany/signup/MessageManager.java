/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.signup;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Student
 */
public class MessageManager {
    // Arrays as requested in the spec (using fixed sizes as per standard array requirements)
    public Message[] sentMessages = new Message[100];
    public Message[] disregardedMessages = new Message[100];
    public Message[] storedMessages = new Message[100];
    public String[] messageHashes = new String[100];
    public String[] messageIDs = new String[100];

    public int sentCount = 0, disCount = 0, storedCount = 0, hashCount = 0, idCount = 0;

    // Add message based on flag
    public void addMessage(Message msg) {
        if (msg.getFlag().equals("Sent") && sentCount < 100) {
            sentMessages[sentCount++] = msg;
        } else if (msg.getFlag().equals("Disregard") && disCount < 100) {
            disregardedMessages[disCount++] = msg;
        } else if (msg.getFlag().equals("Stored") && storedCount < 100) {
            storedMessages[storedCount++] = msg;
            msg.storeMessageToJSON();
        }
        
        if (hashCount < 100) messageHashes[hashCount++] = msg.getMessageHash();
        if (idCount < 100) messageIDs[idCount++] = msg.getMessageID();
    }

    // Load Stored Messages from JSON into the array
    public void loadStoredFromJSON() {
        List<Message> jsonStored = Message.loadStoredMessagesFromJSON();
        for (Message m : jsonStored) {
            if (storedCount < 100) {
                storedMessages[storedCount++] = m;
            }
        }
    }

    // a. Display sender and recipient of all stored messages
    public void displaySenderAndRecipient() {
        System.out.println("--- Sender and Recipients of Stored Messages ---");
        for (int i = 0; i < storedCount; i++) {
            System.out.println("Recipient: " + storedMessages[i].getRecipient());
        }
    }

    // b. Display the longest stored message
    public String getLongestMessage() {
        String longest = "";
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].getMessageText().length() > longest.length()) {
                longest = storedMessages[i].getMessageText();
            }
        }
        return longest;
    }

    // c. Search for a message ID and display recipient and message
    public String searchByMessageID(String searchID) {
        for (int i = 0; i < idCount; i++) {
            if (messageIDs[i].equals(searchID)) {
                // Find the message associated with this ID
                for (int j = 0; j < sentCount; j++) {
                    if (sentMessages[j].getMessageID().equals(searchID)) 
                        return "Recipient: " + sentMessages[j].getRecipient() + " | Message: " + sentMessages[j].getMessageText();
                }
                for (int j = 0; j < storedCount; j++) {
                    if (storedMessages[j].getMessageID().equals(searchID)) 
                        return "Recipient: " + storedMessages[j].getRecipient() + " | Message: " + storedMessages[j].getMessageText();
                }
            }
        }
        return "Message ID not found.";
    }

    // d. Search for all messages stored for a particular recipient
    public void searchByRecipient(String recipientNum) {
        System.out.println("Messages for " + recipientNum + ":");
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].getRecipient().equals(recipientNum)) {
                System.out.println(storedMessages[i].getMessageText());
            }
        }
    }

    // e. Delete a message using the message hash
    public String deleteByHash(String hash) {
        for (int i = 0; i < storedCount; i++) {
            if (storedMessages[i].getMessageHash().equals(hash)) {
                String deletedMsg = storedMessages[i].getMessageText();
                // Shift array elements left to delete
                for (int j = i; j < storedCount - 1; j++) {
                    storedMessages[j] = storedMessages[j + 1];
                }
                storedMessages[storedCount - 1] = null;
                storedCount--;
                return "Message: \"" + deletedMsg + "\" successfully deleted.";
            }
        }
        return "Hash not found.";
    }

    // f. Display a report listing full details of all stored messages
    public void displayReport() {
        System.out.println("--- Stored Messages Report ---");
        for (int i = 0; i < storedCount; i++) {
            System.out.println("Hash: " + storedMessages[i].getMessageHash() + 
                               " | Recipient: " + storedMessages[i].getRecipient() + 
                               " | Message: " + storedMessages[i].getMessageText());
        }
    }
}
    

