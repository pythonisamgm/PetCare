package com.example.PetCare.services.interfaces;

import com.example.PetCare.models.Appointment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    public Appointment createAppoinment(Appointment appointment) throws Exception;
    public ArrayList<Appointment> getAllAppointments();
    public Optional<Appointment> getAppointmentById(Long id);
    public List<Appointment> getAppointmentByType(String typeConsult);
    public void updateAppointment(Appointment appointment, Long id) throws Exception;
    public List<Appointment> getFutureAppointments();
    public List<Appointment> getPastAppointments();
    public void deleteAppointment(Long id);
    public boolean availableDates(LocalDateTime localDateTime);
    public boolean availableDatesForUpdate(LocalDateTime dateTime, Long appointmentId);
    public boolean updateStatusAsPast(Long id);


}




