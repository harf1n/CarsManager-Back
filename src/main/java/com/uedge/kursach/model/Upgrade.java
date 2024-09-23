package com.uedge.kursach.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Upgrade {
    @Id
    private Long id;
    private Boolean turbo;
    private Boolean engineSwap;
    private Boolean chipped;
    private Boolean gearboxSwap;
    private Boolean moddedSuspension;
}
