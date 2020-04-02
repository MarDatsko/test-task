package com.example.traineetask.entity;

import com.example.traineetask.enums.TaskStatus;
import lombok.Data;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Task {

    @Id
    private String id;
    private String title;
    private String description;
    private LocalDateTime lastUpdate;
    private LocalDateTime dateOfCreate;
    private TaskStatus taskStatus;
    private List<User> users;
}
