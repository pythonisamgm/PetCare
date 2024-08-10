package com.example.PetCare.dto.pet;

import com.example.PetCare.dto.guardian.GuardianContactDetailsDTO;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PetContactDetailsDTO {
    private Long petId;
    private String petName;
    private String species;
    //only one guardian or list of guardians?
    private GuardianContactDetailsDTO guardian;

}
