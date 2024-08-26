package com.example.PetCare.dto.guardian;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Name and surname are required.")
    private String guardianName;
    private int telephoneNumber;
    @Email
    private String email;
}
