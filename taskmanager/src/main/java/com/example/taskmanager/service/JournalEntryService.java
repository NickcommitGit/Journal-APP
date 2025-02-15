package com.example.taskmanager.service;

import com.example.taskmanager.entity.JournalEntry;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

@Autowired
    private JournalEntryRepository journalEntryRepository; //dependency injection to create objects-->componentclass

    @Autowired
    private UserService userService;

   @Transactional  //provide atomicity -->also provide isolation//create a container in which all operation are treated as one
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user= userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
           JournalEntry saved= journalEntryRepository.save(journalEntry);
           user.getJournalEntries().add(saved);
           userService.saveUser(user);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);

    }
@Transactional
    public boolean deleteById(ObjectId id, String userName){
       boolean removed= false;
       try {
           User user = userService.findByUserName(userName);
           removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));//next save problem solve
           if (removed) {
               userService.saveUser(user);
               journalEntryRepository.deleteById(id);
           }
       } catch (Exception e) {
           System.out.println(e);
           throw new RuntimeException("An error occurred while deleting the entry",e);
       }
      return removed;
    }

}
