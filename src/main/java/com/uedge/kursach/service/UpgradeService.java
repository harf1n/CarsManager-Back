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
public class UpgradeService {
    @Autowired
    private UpgradeRepository upgradeRepository;

    public Upgrade addUpgrade(Upgrade upgrade) {
        return upgradeRepository.save(upgrade);
    }

    public List<Upgrade> findAllUpgrades(){
        return upgradeRepository.findAll();
    }

    public Upgrade updateUpgrade(Upgrade updatedUpgrade, Upgrade oldUpgrade) {
        oldUpgrade.setTurbo(updatedUpgrade.getTurbo());
        oldUpgrade.setEngineSwap(updatedUpgrade.getEngineSwap());
        oldUpgrade.setChipped(updatedUpgrade.getChipped());
        oldUpgrade.setGearboxSwap(updatedUpgrade.getGearboxSwap());
        oldUpgrade.setModdedSuspension(updatedUpgrade.getModdedSuspension());
        return upgradeRepository.save(oldUpgrade);
    }

    public Upgrade nullUpgrade(Car car){
        Upgrade upgrade = new Upgrade();
        upgrade.setId(car.getId());
        upgrade.setTurbo(false);
        upgrade.setEngineSwap(false);
        upgrade.setChipped(false);
        upgrade.setGearboxSwap(false);
        upgrade.setModdedSuspension(false);
        return upgradeRepository.save(upgrade);
    }

    public Upgrade findUpgradeById(Long id){
        return upgradeRepository.findUpgradeById(id);
    }

    public void deleteUpgradeById(Long id){
        upgradeRepository.deleteById(id);
    }
}
