package com.quotes.handler.services;

import com.quotes.handler.dto.UserDTO;
import com.quotes.handler.entities.User;

public interface UserService {
    void addUser(UserDTO user);
    User getByEmail(String email);
}
