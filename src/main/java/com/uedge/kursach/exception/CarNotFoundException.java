package com.uedge.kursach.exception;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(Long id){
        super("Couldn't found the car with id " + id);

    }
}
