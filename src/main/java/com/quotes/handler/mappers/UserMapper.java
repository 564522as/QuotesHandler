package com.quotes.handler.mappers;

import com.quotes.handler.dto.UserDTO;
import com.quotes.handler.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements EntityAndDTOMapper<User, UserDTO>{
    @Override
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
