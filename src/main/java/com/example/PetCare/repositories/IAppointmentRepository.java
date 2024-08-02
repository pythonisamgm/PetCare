package com.example.PetCare.repositories;

import com.example.PetCare.models.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IAppointmentRepository extends CrudRepository <Appointment,Long>  {
    public boolean updateStatusAsPast(Long id);
    public List<Appointment> getFutureAppointments();
    public List<Appointment> getPastAppointments();

}
