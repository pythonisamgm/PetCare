package com.example.PetCare.dto.guardian;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class GuardianContactDetailsDTO {
    private Long guardianId;
    private String guardianName;
    private int telephoneNumber;
    //@Email
    private String email;
}
