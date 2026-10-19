/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.signup;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Student
 */

public class Message {
    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String message;
    private String messageHash;
    private Object gson;

    public Message(String recipient, String message, int messageCount) {
        this.recipient = recipient;
        this.message = message;
        this.numMessagesSent = messageCount;
        
        Random rand = new Random();
        long idNum = (long) (rand.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        this.messageID = String.valueOf(idNum);
        
        this.messageHash = createMessageHash();
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+[0-9]{9,12}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String createMessageHash() {
        String firstTwoID = messageID.substring(0, 2);
        String count = String.valueOf(numMessagesSent);
        
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "").toUpperCase();
        
        return firstTwoID + ":" + count + ":" + firstWord + lastWord;
    }

    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    public String sentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                storeMessageToJson();
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    public String printMessages() {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + message;
    }

    public int returnTotalMessagess() {
        return numMessagesSent;
    }

    public void storeMessageToJson() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        // Appends to a file named messages.json in the project root
        try (FileWriter writer = new FileWriter("messages.json", true)) {
            gson.toJson(this, writer);
            writer.write("\n"); // Add newline for multiple JSON objects
        } catch (IOException e) {
            System.out.println("Error storing message to JSON: " + e.getMessage());
        }
    }
    
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    
    public void setMessageIDForTesting(String id) {
        this.messageID = id;
        this.messageHash = createMessageHash();
    }

    private static class Gson {

        public Gson() {
        }

        private void toJson(Message aThis, FileWriter writer) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    private static class GsonBuilder {

        public GsonBuilder() {
        }

        private Object setPrettyPrinting() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

}    