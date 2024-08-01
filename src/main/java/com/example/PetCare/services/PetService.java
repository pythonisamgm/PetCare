package com.example.PetCare.services;

import com.example.PetCare.models.Pet;
import com.example.PetCare.repositories.IPetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    IPetRepository iPetRepository;


    public Pet addNewPet(Pet pet) { return iPetRepository.save(pet);}

    public void deletePet(Long id){
        iPetRepository.deleteById(id);
    }

    public List<Pet> listPet() {
        return (List<Pet>)iPetRepository.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return iPetRepository.findById(id);
    }

    public Pet updatePet(Pet pet) {
        return iPetRepository.save(pet);
    }
    public Pet addPet(Pet pet) {return iPetRepository.save(pet);
    }


}