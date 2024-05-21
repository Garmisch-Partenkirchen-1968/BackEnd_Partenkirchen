package com.example.demo.dto.project;

import com.example.demo.Interface.ToUser;
import com.example.demo.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
@Setter
public class GetPermissionDTO implements ToUser {
    private String username;
    private String password;

    public User toUser(){
        if(username == null || password == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return new User(username, password);
    }
}
