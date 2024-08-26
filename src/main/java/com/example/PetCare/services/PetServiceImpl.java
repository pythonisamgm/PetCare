package com.example.PetCare.services;

import com.example.PetCare.dto.guardian.GuardianDTO;
import com.example.PetCare.dto.pet.PetConverter;
import com.example.PetCare.dto.pet.PetDTO;
import com.example.PetCare.dto.pet.PostPetDTO;
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

    @Autowired
    PetConverter petConverter;

    @Override
    public PetDTO createPet(PostPetDTO postPetDTO) {
        Pet pet = petConverter.postDtoToEntity(postPetDTO);
        Pet savedPet = iPetRepository.save(pet);

        return petConverter.entityToDTO(savedPet);}
    @Override
    public List<PetDTO> getAllPets() {
        List<Pet> pets = (List<Pet>) iPetRepository.findAll();

        return pets.stream()
                .map(petConverter::entityToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Optional<PetDTO> getPetById(Long id) {
        Optional<Pet> pet = iPetRepository.findById(id);
        return pet.map(petConverter::entityToDTO);
    }


    @Override
    public PetDTO updatePet(Long id, PetDTO newPetDTO) throws Exception{
        Optional<Pet> optionalOldPet = iPetRepository.findById(id);
        if (!optionalOldPet.isPresent()){
            throw new Exception("Pet not found");
        }
        Pet oldPet = optionalOldPet.get();
        oldPet.setPetName(newPetDTO.getPetName());
        oldPet.setBreed(newPetDTO.getBreed());
        oldPet.setSpecies(newPetDTO.getSpecies());
        oldPet.setGender(newPetDTO.getGender());
        oldPet.setAge(newPetDTO.getAge());
        Pet updatedPet = iPetRepository.save(oldPet);

        return petConverter.entityToDTO(updatedPet);
    }

    @Override
    public void deletePet(Long id){
        iPetRepository.deleteById(id);
    }

    @Override
    public PetDTO getPetByName(String petName) {
        Pet pet = iPetRepository.getPetByName(petName);
        return petConverter.entityToDTO(pet);}



    @Override
    public List<PetDTO> getAllPetsOlderThan(int age) {
        return getAllPets()
                .stream()
                .filter(pet -> pet.getAge() >= age)
                .collect(Collectors.toList());
    }

    @Override
    public List<PetDTO> getAllPetsYoungerThan(int age) {
        return getAllPets()
                .stream()
                .filter(pet -> pet.getAge() < age)
                .collect(Collectors.toList());
    }


    @Override
    public List<PetDTO> getAllPetsBySpecies(String species) {
        return getAllPets()
                .stream()
                .filter(c -> species.equals(c.getSpecies()))
                .collect(Collectors.toList());
    }
}