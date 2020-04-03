package com.example.traineetask.entity;

import com.example.traineetask.enums.UserRole;
import com.example.traineetask.enums.UserStatus;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    private UserRole role;

    private UserStatus userStatus;

    private LocalDateTime lastVisit;

    private LocalDateTime dateOfRegistration;

    private List<Task> tasks;
}
