package com.example.PetCare.services;

import com.example.PetCare.models.Pet;
import com.example.PetCare.repositories.IPetRepository;
import com.example.PetCare.services.interfaces.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl  implements PetService {

    @Autowired
    IPetRepository iPetRepository;


    public Pet createPet(Pet pet) { return iPetRepository.save(pet);}

    public ArrayList<Pet> getAllPets() {
        return (ArrayList<Pet>)iPetRepository.findAll();
    }
    public Optional<Pet> getPetById(Long id) {
        return iPetRepository.findById(id);
    }
    public Pet updatePet(Pet pet) {
        return iPetRepository.save(pet);
    }
    public void deletePet(Long id){
        iPetRepository.deleteById(id);
    }









}