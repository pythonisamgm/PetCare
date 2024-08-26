package com.example.PetCare.repositories;

import com.example.PetCare.models.Appointment;
import com.example.PetCare.models.Guardian;
import com.example.PetCare.models.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IPetRepository extends CrudRepository<Pet,Long>  {

    public Pet getPetByName(String petName);
    public List<Pet> getAllPetsOlderThan(int age);
    public List<Pet> getAllPetsYoungerThan(int age);

    public List<Pet> getAllPetsBySpecies(String species);

}
