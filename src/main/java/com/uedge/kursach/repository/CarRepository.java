package com.uedge.kursach.repository;

import com.uedge.kursach.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
