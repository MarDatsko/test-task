package com.example.traineetask.service.impl;

import com.example.traineetask.dto.TaskDto;
import com.example.traineetask.entity.Task;
import com.example.traineetask.exceptions.NotFoundTaskById;
import com.example.traineetask.exceptions.NotFoundTaskByTitle;
import com.example.traineetask.repository.TaskRepository;
import com.example.traineetask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (byTitle == null){
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
        return taskRepository.save(task);
    }

    @Override
    public void delete(String id) {
        taskRepository.deleteById(id);
    }
}
