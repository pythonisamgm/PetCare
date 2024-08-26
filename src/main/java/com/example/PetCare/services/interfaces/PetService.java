package com.example.PetCare.services.interfaces;

import com.example.PetCare.dto.pet.PetDTO;
import com.example.PetCare.dto.pet.PostPetDTO;
import com.example.PetCare.models.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PetService{
    public PetDTO createPet(PostPetDTO postPetDTO);
    public List<PetDTO> getAllPets();
    public Optional<PetDTO> getPetById(Long id);
    public PetDTO updatePet(Long id, PetDTO petDTO) throws Exception;
    public void deletePet(Long id);
    public PetDTO getPetByName(String petName);
    public List<PetDTO> getAllPetsOlderThan(int age);
    public List<PetDTO> getAllPetsYoungerThan(int age);
    public List<PetDTO> getAllPetsBySpecies(String species);
}
