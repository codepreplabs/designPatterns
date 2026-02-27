package com.codeprep;

public class MediatorDemo
{
    public static void main( String[] args )
    {
        ChatMediator mediator = new ChatRoom();
        ChatUser user1 = new ChatUser("User 1", mediator);
        ChatUser user2 = new ChatUser("User 2", mediator);
        ChatUser user3 = new ChatUser("User 3", mediator);

        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);

        user1.sendMessage("Hello, how are you?");
    }
}
