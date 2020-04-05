package com.example.traineetask.service.impl;

import com.example.traineetask.dto.UserDto;
import com.example.traineetask.entity.Task;
import com.example.traineetask.entity.User;
import com.example.traineetask.exceptions.NotFoundUserByEmail;
import com.example.traineetask.exceptions.NotFoundUserById;
import com.example.traineetask.repository.UserRepository;
import com.example.traineetask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getByID(String id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundUserById(id));
    }

    @Override
    public User getByEmail(String email) {
        User userByEmail = userRepository
                .getUserByEmail(email);
        if (userByEmail == null) {
            throw new NotFoundUserByEmail(email);
        }
        return userByEmail;
    }

    @Override
    public User update(String id, UserDto userDto) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundUserById(id));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void addTaskToUserTasksList(Task task) {
        User userByEmail = userRepository.getUserByEmail(task.getUser().getEmail());
        List<Task> tasks = new ArrayList<>();
        if (userByEmail.getTasks() != null) {
            tasks.addAll(userByEmail.getTasks());
        }
        tasks.add(task);
        userByEmail.setTasks(tasks);
        userRepository.save(userByEmail);
    }
}
