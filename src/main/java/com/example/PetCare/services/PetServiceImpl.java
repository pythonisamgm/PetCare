package com.example.PetCare.services;

import com.example.PetCare.models.Appointment;
import com.example.PetCare.models.Guardian;
import com.example.PetCare.models.Pet;
import com.example.PetCare.repositories.IPetRepository;
import com.example.PetCare.services.interfaces.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl  implements PetService {

    @Autowired
    IPetRepository iPetRepository;

    @Override
    public Pet createPet(Pet pet) { return iPetRepository.save(pet);}
    @Override
    public ArrayList<Pet> getAllPets() {
        return (ArrayList<Pet>)iPetRepository.findAll();
    }
    @Override
    public Optional<Pet> getPetById(Long id) {
        return iPetRepository.findById(id);
    }


    @Override
    public Pet updatePet(Long id, Pet newPet) throws Exception{
        Optional<Pet> optionalOldPet = iPetRepository.findById(id);
        if (!optionalOldPet.isPresent()){
            throw new Exception("Pet not found");
        }
        Pet oldPet = optionalOldPet.get();
        oldPet.setPetName(newPet.getPetName());
        oldPet.setBreed(newPet.getBreed());
        oldPet.setSpecies(newPet.getSpecies());
        oldPet.setGender(newPet.getGender());
        oldPet.setAge(newPet.getAge());
        return iPetRepository.save(oldPet);
    }
    @Override
    public void deletePet(Long id){
        iPetRepository.deleteById(id);
    }

    @Override
    public Optional<Pet> getPetByName(String petName) {
        return iPetRepository.getPetByName(petName);}

    @Override
    public List<Pet> getAllPetsOlderThan(int age) {
        return getAllPets()
                .stream()
                .filter(pet -> pet.getAge() >= age)
                .collect(Collectors.toList());
    }

    @Override
    public List<Pet> getAllPetsYoungerThan(int age) {
        return getAllPets()
                .stream()
                .filter(pet -> pet.getAge() < age)
                .collect(Collectors.toList());
    }


    @Override
    public List<Pet> getAllPetsBySpecies(String species) {
        return getAllPets()
                .stream()
                .filter(c -> species.equals(c.getSpecies()))
                .collect(Collectors.toList());
    }
}