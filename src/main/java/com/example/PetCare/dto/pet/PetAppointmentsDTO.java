package com.example.PetCare.dto.pet;

import com.example.PetCare.dto.appointment.AppointmentDTO;
import com.example.PetCare.dto.guardian.GuardianDTO;
import lombok.*;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PetAppointmentsDTO {
    private Long petId;
    private String petName;
    private String species;
    private GuardianDTO guardian;
    private ArrayList<AppointmentDTO> appointments;
}
