package com.codeprep;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator {

    private List<ChatUser> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void sendMessage(String message, ChatUser sender) {
        for (ChatUser u : users) {
            if (u != sender) {
                u.receive(message);
            }
        }
    }

    @Override
    public void addUser(ChatUser user) {
        users.add(user);
    }

    public void setUsers(List<ChatUser> users) {
        this.users = users;
    }
}
