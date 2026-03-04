package com.codeprep.service.impl;

import com.codeprep.service.IUserService;

public class UserService implements IUserService {

    @Override
    public String getUserDetails(String userName) {
        return "User details for the username: " + userName;
    }
}
