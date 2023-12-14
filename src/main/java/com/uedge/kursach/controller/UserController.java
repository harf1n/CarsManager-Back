package com.uedge.kursach.controller;

import com.uedge.kursach.exception.CarNotFoundException;
import com.uedge.kursach.exception.UserNotFoundException;
import com.uedge.kursach.model.User;
import com.uedge.kursach.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    //инциализация репозитория
    @Autowired
    private UserRepository userRepository;

    //добавление пользователя
    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @PostMapping("/userfind")
    Boolean loginUser(@RequestBody User user){
        User userTemp = userRepository.findUserByUsername(user.getUsername());
        if(userTemp != null){
            return userTemp.getPassword().equals(user.getPassword());
        }
        return false;
    }

    @PostMapping("/username")
    Boolean userExist(@RequestBody User user){
        User userTemp = userRepository.findUserByUsername(user.getUsername());
        if(userTemp != null){
            return true;
        }
        return false;
    }

    //получение всех пользователей
    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //получение пользователя по айди
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    //удаление пользователя
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User with id "+id+" has been deleted.";
    }
}
