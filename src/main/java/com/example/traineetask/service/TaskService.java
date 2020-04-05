package com.example.traineetask.service;

import com.example.traineetask.dto.TaskDto;
import com.example.traineetask.entity.Task;

import java.util.List;

public interface TaskService {

    Task create(Task task);

    Task getByID(String id);

    Task getByTitle(String title);

    Task update(String id, TaskDto taskDto);

    void delete(String id);

    void addContributions(String id, String email);

    List<Task> getAllTasks();
}
