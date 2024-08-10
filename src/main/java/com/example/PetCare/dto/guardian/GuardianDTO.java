package com.example.PetCare.dto.guardian;

import com.example.PetCare.dto.pet.PetDTO;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class GuardianDTO {
    private Long guardianId;
    private String guardianName;
    private int telephoneNumber;
    //@Email
    private String email;
    private LocalDateTime sysRegistryDate;
    private PetDTO pet;
}
