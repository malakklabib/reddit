package com.example.reddit.service;


import com.example.reddit.controller.LinkController;
import com.example.reddit.domain.Users;
import com.example.reddit.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final Logger log = LoggerFactory.getLogger(LinkController.class);
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users register(Users u){
        return u;
    }

    public Users save(Users u){
         return userRepository.save(u);
    }

    @Transactional
    public void saveUsers(Users... u){
        for(Users user: u){
            log.info("Saving User: " + user.getEmail());
            userRepository.save(user);
        }
    }
}
