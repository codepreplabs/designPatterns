package com.codeprep;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Mediator Pattern implementation.
 */
public class MediatorDemoTest {

    private ChatMediator mediator;
    private ChatUser user1;
    private ChatUser user2;
    private ChatUser user3;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        mediator = new ChatRoom();
        user1 = new ChatUser("User 1", mediator);
        user2 = new ChatUser("User 2", mediator);
        user3 = new ChatUser("User 3", mediator);
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
    }

    @BeforeEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Test that all other users receive the message when one user sends it.
     */
    @Test
    public void testMessageBroadcastedToOtherUsers() {
        user1.sendMessage("Hello, how are you?");
        String output = outputStream.toString();

        assertTrue(output.contains("User 2 received message: Hello, how are you?"),
                "User 2 should receive the message");
        assertTrue(output.contains("User 3 received message: Hello, how are you?"),
                "User 3 should receive the message");
    }

    /**
     * Test that the sender does NOT receive their own message.
     */
    @Test
    public void testSenderDoesNotReceiveOwnMessage() {
        user1.sendMessage("Hello, how are you?");
        String output = outputStream.toString();

        assertFalse(output.contains("User 1 received message: Hello, how are you?"),
                "Sender (User 1) should NOT receive their own message");
    }

    /**
     * Test that a user added later also receives subsequent messages.
     */
    @Test
    public void testNewUserReceivesMessage() {
        ChatUser user4 = new ChatUser("User 4", mediator);
        mediator.addUser(user4);

        user1.sendMessage("Welcome, new user!");
        String output = outputStream.toString();

        assertTrue(output.contains("User 4 received message: Welcome, new user!"),
                "Newly added User 4 should receive the message");
    }

    /**
     * Test that no messages are sent when there are no other users.
     */
    @Test
    public void testNoMessageWhenNoOtherUsers() {
        ChatRoom emptyRoom = new ChatRoom();
        ChatUser loneUser = new ChatUser("Lone User", emptyRoom);
        emptyRoom.addUser(loneUser);

        loneUser.sendMessage("Is anyone there?");
        String output = outputStream.toString();

        assertFalse(output.contains("received message"),
                "No one should receive the message when there is only one user");
    }

    /**
     * Test that a ChatRoom can be created and users can be added.
     */
    @Test
    public void testChatRoomAddUser() {
        ChatRoom room = new ChatRoom();
        ChatUser userA = new ChatUser("Alice", room);
        ChatUser userB = new ChatUser("Bob", room);

        assertDoesNotThrow(() -> {
            room.addUser(userA);
            room.addUser(userB);
        });
    }

    /**
     * Test that multiple messages from different users are all correctly routed.
     */
    @Test
    public void testMultipleMessagesBetweenUsers() {
        user1.sendMessage("Hi from User 1");
        user2.sendMessage("Hi from User 2");

        String output = outputStream.toString();

        // User 1's message should reach User 2 and User 3
        assertTrue(output.contains("User 2 received message: Hi from User 1"));
        assertTrue(output.contains("User 3 received message: Hi from User 1"));

        // User 2's message should reach User 1 and User 3
        assertTrue(output.contains("User 1 received message: Hi from User 2"));
        assertTrue(output.contains("User 3 received message: Hi from User 2"));

        // Neither sender should receive their own message
        assertFalse(output.contains("User 1 received message: Hi from User 1"));
        assertFalse(output.contains("User 2 received message: Hi from User 2"));
    }

    /**
     * Test that ChatRoom setUsers replaces the user list.
     */
    @Test
    public void testSetUsersReplacesUserList() {
        ChatRoom room = new ChatRoom();
        ChatUser alice = new ChatUser("Alice", room);
        ChatUser bob = new ChatUser("Bob", room);
        room.addUser(alice);

        List<ChatUser> newUsers = new ArrayList<>();
        newUsers.add(bob);
        room.setUsers(newUsers);

        // Now only bob is in the room; alice sends a message via the room
        alice.sendMessage("Anyone there?");
        String output = outputStream.toString();

        assertTrue(output.contains("Bob received message: Anyone there?"),
                "Bob should receive the message after setUsers");
        assertFalse(output.contains("Alice received message: Anyone there?"),
                "Alice should NOT receive the message after setUsers replaced the list");
    }

    /**
     * Test ChatUser setName updates the user's name for display.
     */
    @Test
    public void testChatUserSetName() {
        ChatRoom room = new ChatRoom();
        ChatUser sender = new ChatUser("OldName", room);
        ChatUser receiver = new ChatUser("Receiver", room);
        room.addUser(sender);
        room.addUser(receiver);

        receiver.setName("NewName");
        sender.sendMessage("Hello!");
        String output = outputStream.toString();

        assertTrue(output.contains("NewName received message: Hello!"),
                "Updated name should appear in received message output");
    }
}
