package com.example.traineetask.entity;

import com.example.traineetask.enums.Role;
import com.example.traineetask.enums.UserStatus;
import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private UserStatus userStatus;
    private LocalDateTime lastVisit;
    private LocalDateTime dateOfRegistration;
    private List<Task> tasks;
}
