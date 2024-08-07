package com.example.PetCare.repositories;

import com.example.PetCare.models.Guardian;
import com.example.PetCare.models.Pet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IPetRepository extends CrudRepository<Pet,Long>  {

    Optional<Pet> getPEtByName(String petName);
    //private int getAllpetsBYAge
    // //get all pets by species
    // ;
}
