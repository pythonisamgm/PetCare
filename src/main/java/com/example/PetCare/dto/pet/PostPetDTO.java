package com.example.PetCare.dto.pet;

import com.example.PetCare.dto.appointment.AppointmentDTO;
import com.example.PetCare.dto.guardian.GuardianDTO;
import com.example.PetCare.models.Appointment;
import lombok.*;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PostPetDTO {
    private Long petId;
    private String petName;
    private int age;
    private String breed;
    private String gender;
    private String species;
    private String url;
    private GuardianDTO guardian;
    private ArrayList<AppointmentDTO> appointments;

}
