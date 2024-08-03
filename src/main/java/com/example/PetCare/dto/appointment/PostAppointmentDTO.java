package com.example.PetCare.dto.appointment;

import com.example.PetCare.dto.pet.PetDTO;
import com.example.PetCare.models.Pet;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PostAppointmentDTO {


    private Long appointmentId;

    private LocalDateTime dateTime;
    //@NotBlank(message = "Consult type is required.")
    //private String consultType;
    //@NotBlank(message = "Insert a detailed description")
    //private String reason;
    private boolean past;
    //@NotNull (message = "Pet is required.")
    private PetDTO pet;
    private AppointmentTypeDTO type;
}
