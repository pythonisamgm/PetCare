package com.example.PetCare.repositories;

import com.example.PetCare.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface IPetRepository extends CrudRepository<Pet,Long>  {
}
