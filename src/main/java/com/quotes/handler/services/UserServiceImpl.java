package com.quotes.handler.services;

import com.quotes.handler.dto.UserDTO;
import com.quotes.handler.entities.User;
import com.quotes.handler.mappers.UserMapper;
import com.quotes.handler.repositories.UserRepository;
import com.quotes.handler.utils.UserAlreadyExistException;
import com.quotes.handler.utils.UserNotExistException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        if (this.userRepository.findByEmail(userDTO.getEmail()).isEmpty()) {
            User user;
            user = userMapper.toEntity(userDTO);
            user.setDateOfCreation(new Date());
            userRepository.save(user);
        } else {
            throw new UserAlreadyExistException("User with this email already exist");
        }
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotExistException("User with this email not exist");
        }
    }
}
