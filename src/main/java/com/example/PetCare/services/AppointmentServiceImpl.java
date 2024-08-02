package com.example.PetCare.services;

import com.example.PetCare.models.Appointment;
import com.example.PetCare.models.Pet;
import com.example.PetCare.repositories.IAppointmentRepository;
import com.example.PetCare.repositories.IPetRepository;
import com.example.PetCare.services.interfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    IAppointmentRepository iAppointmentRepository;
    @Autowired
    IPetRepository iPetRepository;

    @Override
    public Appointment createAppoinment(Appointment appointment) throws Exception {
        Optional<Pet> optionalPet = iPetRepository.findById(appointment.getPet().getPetId();
        if (!optionalPet.isPresent()) {
            throw new Exception("Pet not found");
        }

        Pet pet = optionalPet.get();
        if (availableDates(appointment.getDateTime())) {
            appointment.setPet(pet);
            return iAppointmentRepository.save(appointment);
        } else {
            throw new Exception("Appointment date and time are already taken.");
        }
    }

    @Override
    public ArrayList<Appointment> getAllAppointments() {
        return (ArrayList<Appointment>) iAppointmentRepository.findAll();
    }

    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return iAppointmentRepository.findById(id);
    }

    @Override
    public List<Appointment> getAppointmentByType(String typeConsult) {
        return getAllAppointments()
                .stream()
                .filter(c -> typeConsult.equals(c.getConsultType()))
                .collect(Collectors.toList());
    }

    @Override
    public void updateAppointment(Appointment appointment, Long id) throws Exception{
        Optional<Appointment> existingAppointmentOpt = iAppointmentRepository.findById(id);
        if (!existingAppointmentOpt.isPresent()) {
            throw new Exception("Appointment not found");
        }

        Optional<Pet> optionalPet = iPetRepository.findById(appointment.getPet().getPetId());
        if (!optionalPet.isPresent()) {
            throw new Exception("Pet not found");
        }

        Pet pet = optionalPet.get();

        // Check if the new date and time are available, excluding the current appointment's datetime
        if (availableDatesForUpdate(appointment.getDateTime(), id)) {
            Appointment existingAppointment = existingAppointmentOpt.get();
            existingAppointment.setPet(pet);
            existingAppointment.setDateTime(appointment.getDateTime());
            existingAppointment.setConsultType(appointment.getConsultType());
            existingAppointment.setReason(appointment.getReason());
            existingAppointment.setPast(appointment.isPast());
            iAppointmentRepository.save(existingAppointment);
        } else {
            throw new Exception("Appointment date and time are already taken.");
        }
    }



    public List<Appointment> getFutureAppointments() {
        return getAllAppointments()
                .stream()
                .filter(c -> !c.isPast())
                .collect(Collectors.toList());
    }
    public List<Appointment> getPastAppointments() {
        return getAllAppointments()
                .stream()
                .filter(c -> c.isPast())
                .collect(Collectors.toList());
    }

    public void deleteAppointment(Long id) {
        iAppointmentRepository.deleteById(id);
    }

    public boolean availableDates(LocalDateTime localDateTime) {
        List<Appointment> appointments = (List<Appointment>) iAppointmentRepository.findAll();
        for (Appointment app : appointments) {
            if (app.getDateTime().equals(localDateTime)) {
                return false;
            }
        }
        return true;
    }
    public boolean availableDatesForUpdate(LocalDateTime dateTime, Long appointmentId) {
        List<Appointment> appointments = (List<Appointment>) iAppointmentRepository.findAll();
        for (Appointment app : appointments) {
            if (!app.getAppointmentId().equals(appointmentId) && app.getDateTime().equals(dateTime)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean updateStatusAsPast(Long id) {
        Optional<Appointment> appointmentOptional = iAppointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            LocalDateTime currentDate = LocalDateTime.now();
            LocalDateTime appointmentDate = appointment.getDateTime();

            if (appointmentDate.isBefore(currentDate)) {
                appointment.setPast(true);
                iAppointmentRepository.save(appointment);
                return true;
            }
        }
        return false;
    }
}
