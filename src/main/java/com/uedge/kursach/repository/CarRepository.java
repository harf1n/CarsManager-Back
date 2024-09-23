package com.uedge.kursach.repository;

import com.uedge.kursach.model.Car;
import com.uedge.kursach.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findCarById (Long id);

}
