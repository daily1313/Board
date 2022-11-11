package com.example.board.controller;

import com.example.board.dto.UserDto;
import com.example.board.entity.User;
import com.example.board.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public List<User> findAll() {
        List<User> user = userService.findAll();
        return user;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userId}")
    public User findUser(@PathVariable("userId") Integer userId) {
        User user = userService.findUser(userId);
        return user;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth")
    public User register(@RequestBody UserDto userDto) {
        User user = userService.register(userDto);
        return user;
    }
}
