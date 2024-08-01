package com.example.PetCare.repositories;

import com.example.PetCare.models.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface IAppointmentRepository extends CrudRepository <Appointment,Long>  {
}
