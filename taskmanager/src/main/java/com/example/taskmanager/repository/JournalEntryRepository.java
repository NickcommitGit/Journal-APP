package com.example.taskmanager.repository;

import com.example.taskmanager.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository <JournalEntry, ObjectId> {  //collection-->POJO, id type.


}
