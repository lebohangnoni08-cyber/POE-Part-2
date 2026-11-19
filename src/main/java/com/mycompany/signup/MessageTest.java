/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.signup;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {
    private MessageManager manager;

    @Before
    public void setUp() {
        // This runs before EVERY test to ensure a clean slate
        manager = new MessageManager();
        manager.populateTestData(); // Populates the arrays with the specific Part 3 test data
    }

    @Test
    public void testCreateMessageHash() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?", "Sent");
        String hash = msg.getMessageHash();
        
        assertTrue(hash.contains(":0:"));
        assertTrue(hash.endsWith("HITONIGHT?") || hash.endsWith("HITONIGHT"));
    }

    @Test
    public void testCheckMessageID() {
        Message msg = new Message(1, "08575975889", "Test Message", "Sent");
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testCheckRecipientCell_Valid() {
        Message msg1 = new Message(0, "+27718693002", "Hi Mike...", "Sent");
        Message msg2 = new Message(1, "08575975889", "Hi Keegan...", "Sent");
        
        assertEquals("Cell phone number successfully captured.", msg1.checkRecipientCell());
        assertEquals("Cell phone number successfully captured.", msg2.checkRecipientCell());
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        // Based on the test data, Message 1 and Message 4 are flagged as "Sent"
        assertEquals("Did you get the cake?", manager.sentMessages[0].getMessageText());
        assertEquals("It is dinner time!", manager.sentMessages[1].getMessageText());
    }

    @Test
    public void testDisplayLongestMessage() {
        // Message 2 is the longest in the test data
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, manager.getLongestMessage());
    }

    @Test
    public void testSearchByMessageID() {
        // We grab the auto-generated ID of Message 4 (It is dinner time!)
        String idToSearch = manager.sentMessages[1].getMessageID();
        String result = manager.searchByMessageID(idToSearch);
        
        // Assert that the search returns the correct recipient and message
        assertTrue(result.contains("0838884567"));
        assertTrue(result.contains("It is dinner time!"));
    }

    @Test
    public void testDeleteByHash() {
        // We grab the hash of Message 2 (Where are you? You are late!...) which is stored
        String hashToDelete = manager.storedMessages[0].getMessageHash();
        String expectedResult = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        
        // Test the delete method
        assertEquals(expectedResult, manager.deleteByHash(hashToDelete));
        
        // Verify it was actually deleted from the array (storedCount should drop from 2 to 1)
        assertEquals(1, manager.storedCount);
    }

    @Test
    public void testDisplayReport() {
        // Verifying the array holds the right number of stored items before reporting
        // The test data has 2 "Stored" messages
        assertEquals(2, manager.storedCount); 
    }
}   

