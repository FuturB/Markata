package com.futureB.backend.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Terms")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Terms {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "Terms", columnDefinition = "TEXT")
    private String terms;
}
