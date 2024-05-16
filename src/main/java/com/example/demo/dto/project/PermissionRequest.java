package com.example.demo.dto.project;

import com.example.demo.Interface.ToUser;
import com.example.demo.entity.Project;
import com.example.demo.entity.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Getter
@Builder
public class PermissionRequest implements ToUser {
    private String username;
    private String password;
    private boolean[] permissions;

    public User toUser(){
        if(username == null || password == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new User(username, password);
    }
}
