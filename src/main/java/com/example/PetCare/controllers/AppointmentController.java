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

@RestController
@RequestMapping("/api/v1/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    AppointmentServiceImpl appointmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDTO createAppointment(@RequestBody PostAppointmentDTO postAppointmentDTO) throws Exception {
        logger.info("POST /api/v1/appointments --> " + postAppointmentDTO.toString());
        return appointmentService.createAppointment(postAppointmentDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDTO> getAllAppointments() {
        logger.info("GET /api/v1/appointments");
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<AppointmentDTO> getAppointmentById(@PathVariable Long id) {
        logger.info("GET /api/v1/appointments/" + id);
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/type")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDTO> getAppointmentByType(@RequestParam String typeConsult) {
        logger.info("GET /api/v1/appointments/type?typeConsult=" + typeConsult);
        return appointmentService.getAppointmentByType(typeConsult);
    }

    @GetMapping("/pastAppointments")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDTO> getPastAppointments() {
        logger.info("GET /api/v1/appointments/pastAppointments");
        return appointmentService.getPastAppointments();
    }

    @GetMapping("/futureAppointments")
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDTO> getFutureAppointments() {
        logger.info("GET /api/v1/appointments/futureAppointments");
        return appointmentService.getFutureAppointments();
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateAppointment(@RequestBody AppointmentDTO appointmentDTO, @PathVariable Long id) throws Exception {
        logger.info("PUT /api/v1/appointments/{} --> {}", id, appointmentDTO.toString());
        appointmentService.updateAppointment(appointmentDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAppointment(@PathVariable Long id) {
        logger.info("DELETE /api/v1/appointments/{}", id);
        appointmentService.deleteAppointment(id);
    }
}