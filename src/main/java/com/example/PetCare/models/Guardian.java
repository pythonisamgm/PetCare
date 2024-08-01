package com.example.PetCare.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "Guardian")

public class Guardian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id guardian")
    private Long guardianId;


    @Column(name = "name", nullable = false)
    private String guardianName;

    @Column(name = "telephone number", nullable = false)
    private int telephoneNumber;

    @JsonIgnoreProperties("guardiansList")
    @ManyToOne
    @JoinColumn (name = "idPet", nullable = false)
    private Pet pet;
}