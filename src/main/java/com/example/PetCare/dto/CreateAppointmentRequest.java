package com.example.PetCare.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateAppointmentRequest {


    private LocalDateTime dateTime;
    private String consultType;
    private String reason;
    private boolean status;
    private Long idPet;

}
