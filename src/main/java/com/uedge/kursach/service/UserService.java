package com.uedge.kursach.service;

import com.uedge.kursach.model.Car;
import com.uedge.kursach.model.User;
import com.uedge.kursach.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(Long id){
        return userRepository.findUserById(id);
    }

    public User findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
}