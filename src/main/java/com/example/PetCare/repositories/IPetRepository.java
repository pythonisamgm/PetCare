package com.example.PetCare.repositories;

import com.example.PetCare.models.Appointment;
import com.example.PetCare.models.Guardian;
import com.example.PetCare.models.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IPetRepository extends CrudRepository<Pet,Long>  {

    Optional<Pet> getPetByName(String petName);
    public List<Pet> getAllPetsByAge(int age);
    public List<Pet> getAllPetsBySpecies(String species);

}
