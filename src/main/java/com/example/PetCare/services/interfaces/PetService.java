package com.example.PetCare.services.interfaces;

import com.example.PetCare.models.Pet;

import java.util.ArrayList;
import java.util.Optional;

public interface PetService{
    public Pet createPet(Pet pet);
    public ArrayList<Pet> getAllPets();
    public Optional<Pet> getPetById(Long id);
    public Pet updatePet(Pet pet);
    public void deletePet(Long id);
}
