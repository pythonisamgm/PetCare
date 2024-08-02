package com.example.PetCare.controllers;


import com.example.PetCare.models.Appointment;
import com.example.PetCare.services.PetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    PetServiceImpl petService;

    @PostMapping
    public Pet createPet(@RequestBody Pet pet) {
        return petService.createPet(pet);
    }

    @GetMapping
    public ArrayList<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public Optional<Pet> getPetById(@PathVariable Long id) {
        return petService.getPetById(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }


    @PutMapping("/{id}")
    public Pet updatePet(@PathVariable Long id, @RequestBody Pet petDetails) {
        Pet existingPet = petService.getPetById(id)
                .orElseThrow(() -> new PetNotFoundException("Pet not found with id: " + id));

        existingPet.setPetName(petDetails.getPetName());
        existingPet.setAge(petDetails.getAge());
        existingPet.setBreed(petDetails.getBreed());
        existingPet.setGender(petDetails.getGender());
        existingPet.setSpecies(petDetails.getSpecies());
        existingPet.setUrl(petDetails.getUrl());

        return petService.updatePet(existingPet);
    }
}
