package com.example.taskmanager.repository;

import com.example.taskmanager.entity.JournalEntry;
import com.example.taskmanager.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User, ObjectId> {  //collection-->POJO, id type.

    User findByUserName(String userName);

    void deleteByUserName(String name);
}
