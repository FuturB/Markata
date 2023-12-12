package com.futureB.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "User")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Unit number")
    private String unit_number;

    @Column(name = "Street")
    private String street;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String State;

    @Column(name = "Postal Code")
    private Integer postal_code;

    @Column(name = "Country")
    private String country;

    @ManyToMany
    @JoinTable(
            name = "User_Address",
            joinColumns = @JoinColumn(name = "User_id"),
            inverseJoinColumns = @JoinColumn(name = "Address_id"))
    private List<User> user;
}
