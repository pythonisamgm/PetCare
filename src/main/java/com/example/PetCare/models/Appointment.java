package com.example.PetCare.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table (name = "Appointment")

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id appointment")
    private Long appointmentId;

    @Column(name = "date and time", nullable = false, unique = true)
    private LocalDateTime dateTime;

    @Column(name = "standard/urgent consultation", nullable = false)
    private String consultType;

    @Column(name = "reason of the appointment", nullable = false)
    private String reason;

    @Column(name = "appointment status", nullable = false)
    private boolean past;

    @JsonIgnoreProperties("appointmentsList")
    @ManyToOne
    @JoinColumn (name = "idPet", nullable = false)
    private Pet pet;

}

