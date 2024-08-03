package com.example.PetCare.dto.appointment;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AppointmentTypeDTO {
    private Long appointmentId;
    private String type;
    private String description;

}
