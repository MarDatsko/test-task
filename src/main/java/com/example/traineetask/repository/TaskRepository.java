package com.example.traineetask.repository;

import com.example.traineetask.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<Task,String> {

    Task findByTitle (String title);
}
