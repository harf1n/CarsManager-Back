package com.uedge.kursach.controller;

import com.uedge.kursach.exception.CarNotFoundException;
import com.uedge.kursach.exception.UpgradeNotFoundException;
import com.uedge.kursach.model.Upgrade;
import com.uedge.kursach.repository.UpgradeRepository;
import com.uedge.kursach.service.UpgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/upgrade")
public class UpgradeController {

    @Autowired
    private UpgradeRepository upgradeRepository;
    @Autowired
    private UpgradeService upgradeService;

    @GetMapping("/all")
    List<Upgrade> getAllUpgrades(){
        return upgradeRepository.findAll();
    }

    @GetMapping("/{id}")
    Upgrade getUpgradeById(@PathVariable Long id){
        return upgradeRepository.findById(id)
                .orElseThrow(()->new UpgradeNotFoundException(id));
    }

    @PutMapping("/{id}")
    Upgrade updateCar(@RequestBody Upgrade newUpgrade, @PathVariable Long id){
        return upgradeRepository.findById(id)
                .map(upgrade->{
                    upgrade.setTurbo(newUpgrade.getTurbo());
                    upgrade.setEngineSwap(newUpgrade.getEngineSwap());
                    upgrade.setChipped(newUpgrade.getChipped());
                    upgrade.setGearboxSwap(newUpgrade.getGearboxSwap());
                    upgrade.setModdedSuspension(newUpgrade.getModdedSuspension());
                    return upgradeRepository.save(upgrade);
                }).orElseThrow(()->new CarNotFoundException(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUpgrade(@PathVariable("id") Long id) {
        upgradeService.deleteUpgradeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
