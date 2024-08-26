package com.example.PetCare.controllers;


import com.example.PetCare.dto.appointment.AppointmentDTO;
import com.example.PetCare.dto.guardian.PostGuardianDTO;
import com.example.PetCare.dto.pet.PetConverter;
import com.example.PetCare.dto.pet.PetDTO;
import com.example.PetCare.dto.pet.PostPetDTO;
import com.example.PetCare.models.Appointment;
import com.example.PetCare.models.Guardian;
import com.example.PetCare.services.PetServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.PetCare.exceptions.PetNotFoundException;
import com.example.PetCare.models.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pets")
@CrossOrigin(origins = "*")

public class PetController {
    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    PetServiceImpl petService;

    @Autowired
    PetConverter petConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetDTO createPet(@RequestBody PostPetDTO postPetDTO) {
        logger.info("POST /api/v1/pets --> " + postPetDTO.toString());
        Pet pet = petConverter.postDtoToEntity(postPetDTO);
        Pet createdPet = petService.createPet(pet);
        return petConverter.entityToDTO(createdPet);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PetDTO> getAllPets() {
        logger.info("GET /api/v1/pets");
        List<Pet> pets = petService.getAllPets();
        return pets.stream()
                .map(petConverter::entityToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<PetDTO> getPetById(@PathVariable Long id) {
        logger.info("GET /api/v1/pets/{id}");
        Optional<Pet> pet = petService.getPetById(id);
        return pet.map(petConverter::entityToDTO);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }


    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet petDetails) throws Exception{
        logger.info("PUT /api/v1/pets/{id}");
        Pet existingPet = petService.getPetById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + id));

        existingPet.setPetName(petDetails.getPetName());
        existingPet.setAge(petDetails.getAge());
        existingPet.setBreed(petDetails.getBreed());
        existingPet.setGender(petDetails.getGender());
        existingPet.setSpecies(petDetails.getSpecies());
        existingPet.setUrl(petDetails.getUrl());

        return petService.updatePet(id, existingPet);
    }
    @PutMapping(path = "/{id}")
    public void updateGuardian(@RequestBody PostGuardianDTO postGuardianDTO, @PathVariable Long id) throws Exception {
        logger.info("PUT /api/v1/guardians/{} --> {}", id, postGuardianDTO.toString());
        Guardian request = guardianConverter.postDtoToGuardian(postGuardianDTO);
        guardianService.updateGuardian(id, request);
    }
}
