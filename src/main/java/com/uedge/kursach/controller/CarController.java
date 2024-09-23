package com.uedge.kursach.controller;

import com.uedge.kursach.exception.CarNotFoundException;
import com.uedge.kursach.model.Car;
import com.uedge.kursach.model.Upgrade;
import com.uedge.kursach.model.User;
import com.uedge.kursach.repository.CarRepository;
import com.uedge.kursach.service.CarService;
import com.uedge.kursach.service.UpgradeService;
import com.uedge.kursach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/car")
public class CarController {

    //инциализация репозитория
    @Autowired
    private CarRepository carRepository;
    @Autowired
    UpgradeService upgradeService;
    @Autowired
    private CarService carService;
    @Autowired
    private UserService userService;

    //добавление машины
    @PostMapping("/add")
    Car newCar(@RequestBody Car newCar){
        newCar.setTotalHp(newCar.getHp());
        carRepository.save(newCar);
        newCar.setUpgradeList(upgradeService.nullUpgrade(newCar));
        return carRepository.save(carService.updateCar(newCar.getId(), newCar));
    }

    //получение всех машин
    @GetMapping("/all")
    List<Car> getAllCars(){
        return carRepository.findAll();
    }

    //получение машины по айди
    @GetMapping("/{id}")
    Car getCarById(@PathVariable Long id){
        return carRepository.findById(id)
                .orElseThrow(()->new CarNotFoundException(id));
    }

    @PutMapping("/addUpgrade/{id}")
    public ResponseEntity<Car> addCarUpgrade(@RequestBody Upgrade newUpgrade, @PathVariable("id") Long id) {
        Car car = carService.findCarById(id);
        Upgrade savedUpgrade = upgradeService.updateUpgrade(newUpgrade, car.getUpgradeList());
        car.setUpgradeList(savedUpgrade);
        return new ResponseEntity<>(carService.updateCar(id, car), HttpStatus.OK);
    }

    //изменение данных машины
    @PutMapping("/edit/{id}")
    Car updateCar(@RequestBody Car newCar, @PathVariable Long id){
        return carService.updateCar(id, newCar);
    }

    //удаление машины
    @DeleteMapping("/delete/{username}/{id}")
    String deleteCar(@PathVariable String username, @PathVariable Long id){
        if(!carRepository.existsById(id)){
            throw new CarNotFoundException(id);
        }
        Car car = carRepository.findCarById(id);
        User user = userService.findUserByUsername(username);
        user.getCarList().remove(car);

        userService.addUser(user);
        carRepository.delete(car);
        return "Car with id "+id+" has been deleted.";
    }
}
