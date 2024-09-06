package com.uedge.kursach.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue
    private Long id;
    private String mark;
    private String model;
    private int year;
    private int hp;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Upgrade> upgradeList;


}
