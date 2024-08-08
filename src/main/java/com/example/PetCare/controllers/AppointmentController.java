package com.example.PetCare.controllers;


import com.example.PetCare.dto.appointment.AppointmentConverter;
import com.example.PetCare.dto.appointment.AppointmentDTO;
import com.example.PetCare.dto.appointment.PostAppointmentDTO;
import com.example.PetCare.models.Appointment;
import com.example.PetCare.services.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/appointments")
@CrossOrigin(origins = "*")

public class AppointmentController {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    AppointmentServiceImpl appointmentService;

    @Autowired
    private AppointmentConverter appointmentConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDTO createAppointment(@RequestBody PostAppointmentDTO postAppointmentDTO) throws Exception {
        logger.info("POST /api/v1/appointments --> " + postAppointmentDTO.toString());
        Appointment appointment = appointmentConverter.postDtoToAppointment(postAppointmentDTO);
        Appointment createdAppointment = appointmentService.createAppoinment(appointment);
        return appointmentConverter.appointmentToDto(createdAppointment);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDTO> getAllAppointments() {
        logger.info("GET /api/v1/appointments");
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return appointments.stream()
                .map(appointmentConverter::appointmentToDto)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        logger.info("GET /api/v1/appointments/" + id);
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(appointmentConverter::appointmentToDto);
    }

    @GetMapping("/type")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDTO> getAppointmentByType(@RequestParam String typeConsult) {
        logger.info("GET /api/v1/appointments/type?typeConsult=" + typeConsult);
        List<Appointment> appointments = appointmentService.getAppointmentByType(typeConsult);
        return appointments.stream()
                .map(appointmentConverter::appointmentToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/pastAppointments")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDTO> getPastAppointments() {
        logger.info("GET /api/v1/appointments/pastAppointments");
        List<Appointment> pastAppointments = appointmentService.getPastAppointments();
        return pastAppointments.stream()
                .map(appointmentConverter::appointmentToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/futureAppointments")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDTO> getFutureAppointments(@RequestParam boolean past) {
        logger.info("GET /api/v1/appointments/pastAppointments");
        List<Appointment> futureAppointments = appointmentService.getFutureAppointments();
        return futureAppointments.stream()
                .map(appointmentConverter::appointmentToDto)
                .collect(Collectors.toList());
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAppointment(@RequestBody PostAppointmentDTO postAppointmentDTO, @PathVariable Long id) throws Exception {
        logger.info("PUT /api/v1/appointments/{} --> {}", id, postAppointmentDTO.toString());
        Appointment request = appointmentConverter.postDtoToAppointment(postAppointmentDTO);
        appointmentService.updateAppointment(request, id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable Long id) {
        logger.info("DELETE /api/v1/appointments/{}", id);
        appointmentService.deleteAppointment(id);
    }
}
