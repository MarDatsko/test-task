package com.example.traineetask.dto;

import com.example.traineetask.entity.Task;
import com.example.traineetask.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskForUserDto {
    private String title;
    private String description;
    private LocalDateTime lastUpdate;
    private LocalDateTime dateOfCreate;
    private TaskStatus taskStatus;
    private String userEmail;

    public TaskForUserDto mapTaskToTaskForUserDto(Task task) {
        TaskForUserDto taskForUserDto = new TaskForUserDto();
        taskForUserDto.setTitle(task.getTitle());
        taskForUserDto.setDescription(task.getDescription());
        taskForUserDto.setLastUpdate(task.getLastUpdate());
        taskForUserDto.setDateOfCreate(task.getDateOfCreate());
        taskForUserDto.setTaskStatus(task.getTaskStatus());
        taskForUserDto.setUserEmail(task.getUser().getEmail());

        return taskForUserDto;
    }
}
