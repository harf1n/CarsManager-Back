package com.uedge.kursach.service;

import com.uedge.kursach.model.Car;
import com.uedge.kursach.model.Upgrade;
import com.uedge.kursach.repository.CarRepository;
import com.uedge.kursach.repository.UpgradeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CarService {
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UpgradeRepository upgradeRepository;
    @Autowired
    private UpgradeService upgradeService;

    public Car addCar(Car car){
        return carRepository.save(car);
    }

    public List<Car> findAllCars(){
        return carRepository.findAll();
    }

    public Upgrade getUpgradeByCarId(Long id){
        return upgradeRepository.findAdditionalHpById(id);
    }

    public Car updateCar(Long id, Car updatedCar){
        Car car = carRepository.findCarById(id);
        Upgrade upgrade = getUpgradeByCarId(id);
    // ЧТО ТУТ ДЕЛАТЬ НАДО>>>>>??????
        car.setMark(updatedCar.getMark());
        car.setModel(updatedCar.getModel());
        car.setYear(updatedCar.getYear());
        car.setHp(updatedCar.getHp());
        car.setTotalHp(calcTotalHp(upgrade, updatedCar));
        car.setHandlingScore(upgradedHandScore(id, car.getTotalHp()));
        car.setOverallScore(upgradedOverScore(id, car.getTotalHp(), car.getHandlingScore()));
        return carRepository.save(car);
    }

    public int calcTotalHp(Upgrade upgrade, Car car){
        int addHp = 0;
        int hp = car.getHp();
        if(upgrade.getTurbo()){
            addHp += (int) (hp*1.3-hp);
        }
        if(upgrade.getEngineSwap()){
            addHp += (int) (hp*1.5-hp);
        }
        if(upgrade.getChipped()){
            addHp += (int) (hp*1.15-hp);
        }

        if(addHp==0){
            return hp;
        }
        return addHp+hp;
    }

    public double upgradedOverScore(Long id, int hp, double handScore){
        if(upgradeRepository.findUpgradeById(id).getGearboxSwap()){
            return hp + handScore + 30;
        }
        return hp + handScore;
    }

    public int updatedHandScore(int totalHp){
        return 100 - totalHp/10;
    }

    public double upgradedHandScore(Long id, int totalHp){
        if(upgradeRepository.findUpgradeById(id).getModdedSuspension()){
            return updatedHandScore(totalHp)*1.6;
        }
        return updatedHandScore(totalHp);
    }

    public Car findCarById(Long id){
        return carRepository.findCarById(id);
    }

    public void deleteCarById(Long id){
        carRepository.deleteById(id);
    }

}
