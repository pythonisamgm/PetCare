package com.example.PetCare.services.interfaces;

import com.example.PetCare.models.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PetService{
    public Pet createPet(Pet pet);
    public ArrayList<Pet> getAllPets();
    public Optional<Pet> getPetById(Long id);
    public Pet updatePet(Long id, Pet pet);
    public void deletePet(Long id);
    Optional<Pet> getPetByName(String petName);
    public List<Pet> getAllPetsByAge(int age);
    public List<Pet> getAllPetsBySpecies(String species);
}
