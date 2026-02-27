package com.codeprep;

public interface ChatMediator {
    void sendMessage(String message, ChatUser user);
    void addUser(ChatUser user);
}
