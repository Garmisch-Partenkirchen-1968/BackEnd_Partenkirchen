package com.example.demo.dto.issue;

import com.example.demo.entity.enumerate.IssuePriority;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class IssuePostRequest {
    private String username;
    private String password;
    private String title;
    private IssuePriority priority;
}
