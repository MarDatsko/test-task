package com.example.traineetask.service.impl;

import com.example.traineetask.dto.TaskDto;
import com.example.traineetask.entity.Task;
import com.example.traineetask.exceptions.NotFoundTaskById;
import com.example.traineetask.exceptions.NotFoundTaskByTitle;
import com.example.traineetask.repository.TaskRepository;
import com.example.traineetask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getByID(String id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundTaskById(id));
    }

    @Override
    public Task getByTitle(String title) {
        Task byTitle = taskRepository
                .findByTitle(title);
        if (byTitle == null) {
            throw new NotFoundTaskByTitle(title);
        }
        return byTitle;
    }

    @Override
    public Task update(String id, TaskDto taskDto) {
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundTaskById(id));
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setLastUpdate(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public void delete(String id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void addContributions(String id, String email) {
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundTaskById(id));
        List<String> listContributions = new ArrayList<>();
        if (task.getListContribution() != null) {
            listContributions.addAll(task.getListContribution());
        }
        listContributions.add(email);
        task.setListContribution(listContributions);
        task.setLastUpdate(LocalDateTime.now());
        taskRepository.save(task);
    }
}
