package com.example.PetCare.controllers;


import com.example.PetCare.dto.CreateAppointmentRequest;
import com.example.PetCare.models.Appointment;
import com.example.PetCare.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "*")

public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @PostMapping("/appointments")
    public Appointment createAppointment(@RequestBody CreateAppointmentRequest request) throws Exception {
        return appointmentService.createAppoinment(request);
    }

    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/appointments/{id}")
    public Optional<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/appointments/type")
    public List<Appointment> getAppointmentByType(@RequestParam String typeConsult) {
        return appointmentService.getAppointmentByType(typeConsult);
    }

    @GetMapping("/appointments/status")
    public List<Appointment> getAppointmentByStatus(@RequestParam String status) {
        return appointmentService.getAppointmentByStatus(status);
    }
    @PutMapping(path = "/appointments/{id}")
    public void updateAppointment(@RequestBody Appointment appointment, @PathVariable Long id){
        appointmentService.updateAppointment(appointment, id);
    }

}
