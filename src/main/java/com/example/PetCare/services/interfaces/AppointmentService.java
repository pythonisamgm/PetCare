package com.example.PetCare.services.interfaces;

import com.example.PetCare.dto.appointment.AppointmentDTO;
import com.example.PetCare.dto.appointment.PostAppointmentDTO;
import com.example.PetCare.models.Appointment;
import com.example.PetCare.repositories.IAppointmentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AppointmentService  {


    public AppointmentDTO createAppointment(PostAppointmentDTO postAppointmentDTO) throws Exception;

    public List<AppointmentDTO> getAllAppointments();
    public Optional<AppointmentDTO> getAppointmentById(Long id);
    public List<AppointmentDTO> getAppointmentByType(String typeConsult);
    public void updateAppointment(AppointmentDTO appointmentDTO, Long id) throws Exception;
    public List<AppointmentDTO> getFutureAppointments();
    public List<AppointmentDTO> getPastAppointments();
    public void deleteAppointment(Long id);
    public boolean availableDates(LocalDateTime localDateTime);
    public boolean availableDatesForUpdate(LocalDateTime dateTime, Long appointmentId);
    public boolean updateStatusAsPast(Long id);


}




