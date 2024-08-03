package com.example.PetCare.dto.guardian;

import com.example.PetCare.dto.pet.PetDTO;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class GuardianDTO {
    private Long guardianId;
    private String guardianName;
    private int telephoneNumber;
    //@Email
    private String email;
    private LocalDateTime sysRegistryDate;
    private PetDTO pet;
}
