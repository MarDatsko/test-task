package com.example.traineetask.repository;

import com.example.traineetask.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task,String> {
}
