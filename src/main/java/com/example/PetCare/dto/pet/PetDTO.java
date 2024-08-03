package com.example.PetCare.dto.pet;

import com.example.PetCare.dto.guardian.GuardianDTO;
import com.example.PetCare.models.Appointment;
import com.example.PetCare.models.Guardian;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public class PetDTO{
    private Long petId;
    private String petName;
    private int age;
    private String breed;
    private String gender;
    private String species;
    private String url;
    private GuardianDTO guardian;
    private ArrayList<Appointment> appointments;
}
