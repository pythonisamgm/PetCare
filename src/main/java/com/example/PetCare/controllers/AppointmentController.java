package com.example.PetCare.controllers;


import com.example.PetCare.models.Appointment;
import com.example.PetCare.services.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/appointments")
@CrossOrigin(origins = "*")

public class AppointmentController {
    @Autowired
    AppointmentServiceImpl appointmentService;
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) throws Exception {
        return appointmentService.createAppoinment(appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Optional<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/type")
    public List<Appointment> getAppointmentByType(@RequestParam String typeConsult) {
        return appointmentService.getAppointmentByType(typeConsult);
    }

    @GetMapping("/pastAppointments")
    public List<Appointment> getPastAppointments(@RequestParam boolean past) {
        return appointmentService.getPastAppointments();
    }
    @GetMapping("/futureAppointments")
    public List<Appointment> getFutureAppointments(@RequestParam boolean past) {
        return appointmentService.getFutureAppointments();
    }
    @PutMapping(path = "/{id}")
    public void updateAppointment(@RequestBody Appointment appointment, @PathVariable Long id) throws Exception{
        appointmentService.updateAppointment(appointment, id);
    }

}
