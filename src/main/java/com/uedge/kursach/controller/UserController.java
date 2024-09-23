package com.uedge.kursach.controller;

import com.uedge.kursach.exception.UserNotFoundException;
import com.uedge.kursach.model.Car;
import com.uedge.kursach.model.User;
import com.uedge.kursach.repository.UserRepository;
import com.uedge.kursach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    //инциализация репозитория
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    private CarController carController;

    //добавление пользователя
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        Car car = null;
        newUser.getCarList().add(car);
        User user = userService.addUser(newUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/find")
    Boolean loginUser(@RequestBody User user){
        User userTemp = userService.findUserByUsername(user.getUsername());
        if(userTemp != null){
            return userTemp.getPassword().equals(user.getPassword());
        }
        return false;
    }

    @PostMapping("/findId")
    public Long loginUserId(@RequestBody User user){
        User userTemp = userService.findUserByUsername(user.getUsername());
        return userTemp.getId();
    }

    @PostMapping("/money")
    public int getUserMoney(@RequestBody User user){
        User userTemp = userService.findUserByUsername(user.getUsername());
        if(userTemp != null){
            return userTemp.getMoney();
        }
        return 0;
    }

    @GetMapping("/isExist")
    Boolean userExist(User user){
        User userTemp = userService.findUserByUsername(user.getUsername());
        if(userTemp != null){
            return true;
        }
        return false;
    }

    @GetMapping("/{username}")
    User getUserById(@PathVariable String username){
        return userService.findUserByUsername(username);
    }

    @GetMapping("/cars/{username}")
    List<Car> getUserCars(@PathVariable String username){
        User user = userService.findUserByUsername(username);
        return user.getCarList();
    }

    //получение всех пользователей
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user = userService.findAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/addCar/{username}")
    public ResponseEntity<User> addUserCar(@RequestBody Car newCar, @PathVariable("username") String username) {
        Car savedCar = carController.newCar(newCar);
        User user = userService.findUserByUsername(username);
        user.getCarList().add(savedCar);
        userService.addUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}