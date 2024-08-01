package com.example.PetCare.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table (name = "Pet")

public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet id")
    private Long petId;

    @Column(name = "name", nullable = false)
    private String petName;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "breed", nullable = false)
    private String breed;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "species")
    private String species;

    @Column(name = "url")
    private String url;

    @JsonIgnoreProperties("pet")
    @OneToMany(mappedBy = "pet")
    private List<Guardian> guardiansList;

    @JsonIgnoreProperties("pet")
    @OneToMany(mappedBy = "pet")
    private List<Appointment> appointmentsList;


}

