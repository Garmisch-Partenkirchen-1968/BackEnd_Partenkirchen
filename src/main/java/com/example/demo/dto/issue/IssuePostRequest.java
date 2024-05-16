package com.example.demo.dto.issue;

import com.example.demo.Interface.ToUser;
import com.example.demo.entity.User;
import com.example.demo.entity.enumerate.IssuePriority;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Builder
public class IssuePostRequest implements ToUser {
    private String username;
    private String password;
    private String title;
    private IssuePriority priority;

    public User toUser(){
        if(username == null || password == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return new User(username, password);
    }

    public String getUsername(){
        if(username == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return this.username;
    }

    public String getPassword(){
        if(password == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return this.password;
    }

    public String getTitle(){
        if(title == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return this.title;
    }

    public IssuePriority getPriority(){
        if(priority == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return this.priority;
    }
}
