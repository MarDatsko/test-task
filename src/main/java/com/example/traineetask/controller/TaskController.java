package com.example.traineetask.controller;

import com.example.traineetask.dto.TaskDto;
import com.example.traineetask.dto.TaskForUserDto;
import com.example.traineetask.entity.Task;
import com.example.traineetask.enums.TaskStatus;
import com.example.traineetask.security.JwtUtil;
import com.example.traineetask.service.TaskService;
import com.example.traineetask.service.UserService;
import com.example.traineetask.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public TaskController(TaskServiceImpl taskService, UserService userService, JwtUtil jwtUtil) {
        this.taskService = taskService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<List<TaskForUserDto>> getAllTask() {
        List<TaskForUserDto> tasks = new ArrayList<>();
        if (taskService.getAllTasks().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(tasks);
        }
        taskService.getAllTasks().forEach(task -> tasks.add(new TaskForUserDto().mapTaskToTaskForUserDto(task)));
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<String> addNewTask(@RequestBody TaskDto taskDto, HttpServletRequest httpServletRequest) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setDateOfCreate(LocalDateTime.now());
        task.setLastUpdate(LocalDateTime.now());
        task.setTaskStatus(TaskStatus.IN_PROCESS);
        task.setUser(userService.getByEmail(getEmailWithHttpRequest(httpServletRequest)));
        taskService.create(task);
        userService.addTaskToUserTasksList(task);
        return ResponseEntity.ok("Task created successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> editTask(@PathVariable(name = "id") String id, @RequestBody TaskDto taskDto) {
        taskService.update(id, taskDto);
        return ResponseEntity.ok("Task updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable(name = "id") String id) {
        taskService.delete(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

    @PostMapping("/addContributions/{taskId}")
    public ResponseEntity<String> addContribution(@PathVariable(name = "taskId") String taskId,
                                                  @RequestParam(name = "email") String email) {
        taskService.addContributions(taskId, email);
        return ResponseEntity.ok("Email successfully added to contributions");
    }

    private String getEmailWithHttpRequest(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        String email = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            email = jwtUtil.extractEmail(jwt);
        }

        return email;
    }
}
