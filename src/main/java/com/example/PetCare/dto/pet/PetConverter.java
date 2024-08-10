package com.example.PetCare.dto.pet;

import com.example.PetCare.dto.appointment.AppointmentDTO;
import com.example.PetCare.dto.guardian.GuardianContactDetailsDTO;
import com.example.PetCare.dto.guardian.GuardianDTO;
import com.example.PetCare.dto.guardian.PostGuardianDTO;
import com.example.PetCare.models.Appointment;
import com.example.PetCare.models.Guardian;
import com.example.PetCare.models.Pet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class PetConverter {
    @Autowired
    ModelMapper modelMapper;

    public PetDTO entityToDTO(Pet pet){
        PetDTO petDTO = modelMapper.map(pet, PetDTO.class);
        if (pet.getGuardiansList() != null && !pet.getGuardiansList().isEmpty()){
            GuardianDTO guardianDTO = modelMapper.map(pet.getGuardiansList().get(0), GuardianDTO.class);
            petDTO.setGuardian(guardianDTO);
        }
        if (pet.getAppointmentsList() != null){
            petDTO.setAppointments(pet.getAppointmentsList().stream()
                    .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                    .collect(Collectors.toCollection(ArrayList::new)));
        }
        return petDTO;
    }
    public Pet petDtoToEntity(PetDTO petDTO){
        return modelMapper.map(petDTO, Pet.class);
    }
    public PetAppointmentsDTO petAppointmentsDTO(Pet pet){
        PetAppointmentsDTO petAppointmentsDTO = modelMapper.map(pet, PetAppointmentsDTO.class);

        if (pet.getAppointmentsList() != null){
            petAppointmentsDTO.setAppointments(pet.getAppointmentsList().stream()
                    .map(appointment -> modelMapper.map(appointment,AppointmentDTO.class))
                    .collect(Collectors.toCollection(ArrayList::new)));
        }
        return petAppointmentsDTO;
    }
    public Pet petAppointmentsDtoToEntity(PetAppointmentsDTO petAppointmentsDTO){
        return modelMapper.map(petAppointmentsDTO, Pet.class);
    }
    public PetContactDetailsDTO petToContactDetailsDTO(Pet pet){
        PetContactDetailsDTO petContactDetailsDTO = modelMapper.map(pet, PetContactDetailsDTO.class);
        if (pet.getGuardiansList() != null && !pet.getGuardiansList().isEmpty()){
            GuardianContactDetailsDTO guardianDTO = modelMapper.map(pet.getGuardiansList().get(0), GuardianContactDetailsDTO.class);
            petContactDetailsDTO.setGuardian(guardianDTO);
        }
        return petContactDetailsDTO;
    }
    public Pet contactDetailsToDTO (PetContactDetailsDTO petContactDetailsDTO){
        return modelMapper.map(petContactDetailsDTO, Pet.class);
    }
    public PostPetDTO entityToPostDto(Pet pet){
        PostPetDTO postPetDTO = modelMapper.map(pet, PostPetDTO.class);
        if (pet.getGuardiansList() != null && !pet.getGuardiansList().isEmpty()){
            GuardianDTO guardianDTO = modelMapper.map(pet.getGuardiansList().get(0),GuardianDTO.class);
            postPetDTO.setGuardian(guardianDTO);
        }
        if (pet.getAppointmentsList() != null){
            postPetDTO.setAppointments(pet.getAppointmentsList().stream()
                    .map(appointment -> modelMapper.map(appointment, AppointmentDTO.class))
                    .collect(Collectors.toCollection(ArrayList::new)));
        }
        return postPetDTO;
    }
    public Pet postDtoToEntity (PostPetDTO postPetDTO){
        Pet pet = modelMapper.map(postPetDTO, Pet.class);

        if (postPetDTO.getGuardian() != null){
            GuardianDTO guardianDTO = postPetDTO.getGuardian();
            pet.setGuardiansList(new ArrayList<>());
            pet.getGuardiansList().add(modelMapper.map(guardianDTO, Guardian.class ));
        }
        if (postPetDTO.getAppointments() != null){
            pet.setAppointmentsList(postPetDTO.getAppointments().stream()
                    .map (appointmentDTO -> modelMapper.map(appointmentDTO, Appointment.class))
                    .collect(Collectors.toCollection(ArrayList::new)));
        }return pet;
    }
}
