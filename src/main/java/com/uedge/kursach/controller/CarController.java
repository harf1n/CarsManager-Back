package com.uedge.kursach.controller;

import com.uedge.kursach.exception.CarNotFoundException;
import com.uedge.kursach.model.Car;
import com.uedge.kursach.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CarController {

    //инциализация репозитория
    @Autowired
    private CarRepository carRepository;

    //добавление машины
    @PostMapping("/car")
    Car newCar(@RequestBody Car newCar){
        return carRepository.save(newCar);
    }

    //получение всех машин
    @GetMapping("/cars")
    List<Car> getAllCars(){
        return carRepository.findAll();
    }

    //получение машины по айди
    @GetMapping("/car/{id}")
    Car getCarById(@PathVariable Long id){
        return carRepository.findById(id)
                .orElseThrow(()->new CarNotFoundException(id));
    }
    //изменение данных машины
    @PutMapping("/car/{id}")
    Car updateCar(@RequestBody Car newCar, @PathVariable Long id){
        return carRepository.findById(id)
                .map(car->{
                    car.setMark(newCar.getMark());
                    car.setModel(newCar.getModel());
                    car.setYear(newCar.getYear());
                    car.setHp(newCar.getHp());
                    return carRepository.save(car);
                }).orElseThrow(()->new CarNotFoundException(id));
    }
    //удаление машины
    @DeleteMapping("/car/{id}")
    String deleteCar(@PathVariable Long id){
        if(!carRepository.existsById(id)){
            throw new CarNotFoundException(id);
        }
        carRepository.deleteById(id);
        return "Car with id "+id+" has been deleted.";
    }
}
