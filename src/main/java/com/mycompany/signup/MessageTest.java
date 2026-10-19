/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.signup;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author Student
 */
public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27718693002", "Hi Kea, can you join us for dinner tonight?", 0);
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        // Create a string of 255 characters
        String longMessage = "A".repeat(255); 
        Message msg = new Message("+27718693002", longMessage, 0);
        assertEquals("Message exceeds 250 characters by 5; please reduce the size.", msg.checkMessageLength());
    }

    @Test
    public void testRecipientCorrectlyFormatted() {
        Message msg = new Message("+27718693002", "Test Message", 0);
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientIncorrectlyFormatted() {
        Message msg = new Message("08575975889", "Test Message", 0); // No +27
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashCorrect() {
        Message msg = new Message("+27718693002", "Hi Kea, can you join us for dinner tonight?", 0);
        // Force ID to start with 00 to match the expected test output
        msg.setMessageIDForTesting("0012345678"); 
        assertEquals("00:0:HITONIGHT", msg.getMessageHash());
    }

    @Test
    public void testCheckMessageID() {
        Message msg = new Message("+27718693002", "Test", 0);
        assertTrue(msg.checkMessageID());
    }
    
    @Test
    public void testSentMessageOptions() {
        Message msg = new Message("+27718693002", "Test", 0);
        assertEquals("Message successfully sent.", msg.sentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
    
    @Test
    public void testReturnTotalMessages() {
        Message msg = new Message("+27718693002", "Test", 2);
        assertEquals(2, msg.returnTotalMessagess());
    }
}
    

