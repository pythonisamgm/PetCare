package com.example.PetCare.controllers;


import com.example.PetCare.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.PetCare.exceptions.PetNotFoundException;
import com.example.PetCare.models.Pet;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pet")
@CrossOrigin(origins = "*")

public class PetController {
    @Autowired
    PetService petService;

    @PostMapping(path = "/pets")
    public Pet addNewPet(@RequestBody Pet pet) {
        return petService.addNewPet(pet);
    }

    @DeleteMapping(path = "pets/{id}")
    public void deletePet(@PathVariable Long id) {
        petService.deletePet(id);
    }

    @GetMapping
    public List<Pet> listPet() {
        return petService.listPet().stream()
                .map(Pet::new)
                .collect(Collectors.toList());
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
