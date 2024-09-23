package com.uedge.kursach.repository;

import com.uedge.kursach.model.Upgrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpgradeRepository extends JpaRepository<Upgrade, Long> {

    Upgrade findUpgradeById(Long id);
    Upgrade findAdditionalHpById(Long id);
}
