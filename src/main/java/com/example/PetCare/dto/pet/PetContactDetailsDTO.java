package com.example.PetCare.dto.pet;

import com.example.PetCare.dto.guardian.GuardianContactDetailsDTO;
import com.example.PetCare.dto.guardian.GuardianDTO;
import com.example.PetCare.models.Appointment;

import java.util.ArrayList;

public class PetContactDetailsDTO {
    private Long petId;
    private String petName;
    private String species;
    private GuardianContactDetailsDTO guardian;

}
