package com.example.PetCare.services;

import com.example.PetCare.dto.appointment.AppointmentConverter;
import com.example.PetCare.dto.appointment.AppointmentDTO;
import com.example.PetCare.dto.appointment.PostAppointmentDTO;
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
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    IAppointmentRepository iAppointmentRepository;
    @Autowired
    IPetRepository iPetRepository;
    @Autowired
    AppointmentConverter appointmentConverter;

    @Override
    public AppointmentDTO createAppointment(PostAppointmentDTO postAppointmentDTO) throws Exception {
        Appointment appointment = appointmentConverter.postDtoToAppointment(postAppointmentDTO);
        Optional<Pet> optionalPet = iPetRepository.findById(appointment.getPet().getPetId());
        if (!optionalPet.isPresent()) {
            throw new Exception("Pet not found");
        }

        Pet pet = optionalPet.get();
        if (availableDates(appointment.getDateTime())) {
            appointment.setPet(pet);
            Appointment createdAppointment = iAppointmentRepository.save(appointment);
            return appointmentConverter.appointmentToDto(createdAppointment);
        } else {
            throw new Exception("Appointment date and time are already taken.");
        }
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> appointments = (List<Appointment>) iAppointmentRepository.findAll();
        return appointments.stream()
                .map(appointmentConverter::appointmentToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AppointmentDTO> getAppointmentById(Long id) {
        Optional<Appointment> appointment = iAppointmentRepository.findById(id);
        return appointment.map(appointmentConverter::appointmentToDto);
    }

    @Override
    public List<AppointmentDTO> getAppointmentByType(String typeConsult) {
        List<Appointment> appointments = (List<Appointment>)iAppointmentRepository.findAll();
        List<Appointment> filteredAppointments = appointments
                .stream()
                .filter(appointment -> typeConsult.equals(appointment.getConsultType()))
                .collect(Collectors.toList());
        return filteredAppointments.stream()
                .map(appointmentConverter::appointmentToDto)
                .collect(Collectors.toList());
    }


    @Override
    public void updateAppointment(AppointmentDTO appointmentDTO, Long id) throws Exception {
        Optional<Appointment> existingAppointmentOpt = iAppointmentRepository.findById(id);
        if (!existingAppointmentOpt.isPresent()) {
            throw new Exception("Appointment not found");
        }

        Optional<Pet> optionalPet = iPetRepository.findById(appointmentDTO.getPet().getPetId());
        if (!optionalPet.isPresent()) {
            throw new Exception("Pet not found");
        }

        Pet pet = optionalPet.get();

        // Check if the new date and time are available, excluding the current appointment's datetime
        if (availableDatesForUpdate(appointmentDTO.getDateTime(), id)) {
            Appointment existingAppointment = existingAppointmentOpt.get();
            existingAppointment.setPet(pet);
            existingAppointment.setDateTime(appointmentDTO.getDateTime());
            existingAppointment.setConsultType(appointmentDTO.getConsultType());
            existingAppointment.setReason(appointmentDTO.getReason());
            existingAppointment.setPast(appointmentDTO.isPast());
            iAppointmentRepository.save(existingAppointment);
        } else {
            throw new Exception("Appointment date and time are already taken.");
        }
    }


    @Override
    public List<AppointmentDTO> getFutureAppointments() {
        return getAllAppointments()
                .stream()
                .filter(c -> !c.isPast())
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getPastAppointments() {
        return getAllAppointments()
                .stream()
                .filter(c -> c.isPast())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAppointment(Long id) {
        iAppointmentRepository.deleteById(id);
    }

    @Override
    public boolean availableDates(LocalDateTime localDateTime) {
        List<Appointment> appointments = (List<Appointment>) iAppointmentRepository.findAll();
        for (Appointment app : appointments) {
            if (app.getDateTime().equals(localDateTime)) {
                return false;
            }
        }
        return true;
    }

    @Override
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
