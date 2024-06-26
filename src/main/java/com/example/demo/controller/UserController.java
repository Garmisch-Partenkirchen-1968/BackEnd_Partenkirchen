package com.example.demo.controller;

import com.example.demo.dto.user.UserSignInResponse;
import com.example.demo.dto.user.UserSignupRequest;
import com.example.demo.dto.user.UserUpdatePasswordRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody UserSignupRequest userSignupRequest) {
        userService.signUpUser(userSignupRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/signin")
    public UserSignInResponse signIn(@RequestParam(value = "username", defaultValue = "") String username,
                                     @RequestParam(value = "password", defaultValue = "") String password) {
        User user = new User(username, password);
        return userService.signInUser(user);
    }

    @PatchMapping("/user/{userId}")
    public void updateUser(@PathVariable("userId") Long userId, @RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest) {
        if (!Objects.equals(userId, checkPermission(userUpdatePasswordRequest.toUser()))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "wrong permission");
        }
        userService.updatePassword(userId, userUpdatePasswordRequest.getNewPassword());
    }

    @DeleteMapping("/user/{userId}")
    public void delete(@PathVariable Long userId, @RequestBody User user) {
        if (!Objects.equals(userId, checkPermission(user))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "wrong permission");
        }
        userService.deleteUser(userId);
    }

    @GetMapping("/users")
    public List<User> getUser(@RequestParam(value = "keyword", defaultValue = "") String keyword,
                              @RequestParam(value = "username", defaultValue = "") String username,
                              @RequestParam(value = "password", defaultValue = "") String password) {
        User user = new User(username, password);
        checkPermission(user);
        return userService.getUsers(keyword);
    }

    private Long checkPermission(User user) {
        UserSignInResponse foundUser = userService.signInUser(user);
        return foundUser.getId();
    }
}
