package com.example.PetCare.dto.appointment;

import com.example.PetCare.dto.pet.PetDTO;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class GetAppointmentDTO {

        private Long appointmentId;
        private LocalDateTime dateTime;
        private String consultType;
        private String reason;
        private boolean isPast;
        private PetDTO pet;
        private AppointmentTypeDTO type;

}
