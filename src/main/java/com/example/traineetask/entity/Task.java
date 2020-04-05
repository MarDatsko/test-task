package com.example.traineetask.entity;

import com.example.traineetask.enums.TaskStatus;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "tasks")
public class Task {

    @Id
    private String id;

    @NotBlank
    private String title;

    private String description;

    private LocalDateTime lastUpdate;

    private LocalDateTime dateOfCreate;

    private TaskStatus taskStatus;

    private List<String> listContribution;

    @DBRef
    private User user;
}
