package com.example.traineetask.service;

import com.example.traineetask.dto.UserDto;
import com.example.traineetask.entity.Task;
import com.example.traineetask.entity.User;

public interface UserService {

    User create(User user);

    User getByID(String id);

    User getByEmail(String email);

    User update(String id, UserDto userDto);

    void delete(String id);

    void addTaskToUserTasksList(Task task);
}
