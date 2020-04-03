package com.example.traineetask.controller;

import com.example.traineetask.dto.TaskDto;
import com.example.traineetask.entity.Task;
import com.example.traineetask.enums.TaskStatus;
import com.example.traineetask.service.TaskService;
import com.example.traineetask.service.impl.TaskServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<Task> addNewTask(@RequestBody TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDateOfCreate(LocalDateTime.now());
        task.setLastUpdate(LocalDateTime.now());
        task.setTaskStatus(TaskStatus.IN_PROCESS);
        // task.setUserId();


        return ResponseEntity.ok(task);
    }
}
