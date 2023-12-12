package com.quotes.handler.controllers;

import com.quotes.handler.dto.UserDTO;
import com.quotes.handler.entities.User;
import com.quotes.handler.services.UserService;
import com.quotes.handler.utils.InvalidQuoteException;
import com.quotes.handler.utils.InvalidUserDataException;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpEntity<String> add(@RequestBody @Valid UserDTO user,
                                  BindingResult bindingResult) {
        checkBindingResultOnErrors(bindingResult);
        userService.addUser(user);
        return new HttpEntity<>("Ok");
    }
    public void checkBindingResultOnErrors(BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            StringBuilder message = new StringBuilder();
            message.append("message: ");
            for (FieldError error: bindingResult.getFieldErrors()) {
                message.append(error.getField());
                message.append(" - ");
                message.append(error.getDefaultMessage());
                message.append("; ");
            }
            throw new InvalidUserDataException(message.toString());
        }
    }
}
