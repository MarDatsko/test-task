package com.example.traineetask.repository;

import com.example.traineetask.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    User getUserByEmail(String email);
}
