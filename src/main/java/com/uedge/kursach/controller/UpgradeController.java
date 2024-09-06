package com.uedge.kursach.controller;

import com.uedge.kursach.exception.UpgradeNotFoundException;
import com.uedge.kursach.model.Upgrade;
import com.uedge.kursach.repository.UpgradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UpgradeController {

    @Autowired
    private UpgradeRepository upgradeRepository;

    @PostMapping("/upgrade")
    Upgrade newUpgrade(@RequestBody Upgrade newUpgrade){
        return upgradeRepository.save(newUpgrade);
    }

    @GetMapping("/upgrades")
    List<Upgrade> getAllUpgrades(){
        return upgradeRepository.findAll();
    }

    @GetMapping("/upgrade/{id}")
    Upgrade getUpgradeById(@PathVariable Long id){
        return upgradeRepository.findById(id)
                .orElseThrow(()->new UpgradeNotFoundException(id));
    }

    @DeleteMapping("/upgrade/{id}")
    String deleteUpgrade(@PathVariable Long id){
        if(!upgradeRepository.existsById(id)){
            throw new UpgradeNotFoundException(id);
        }
        upgradeRepository.deleteById(id);
        return "Upgrade with id "+id+" has been deleted.";
    }
}
