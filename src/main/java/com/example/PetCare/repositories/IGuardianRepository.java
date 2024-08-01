package com.example.PetCare.repositories;

import com.example.PetCare.models.Guardian;
import org.springframework.data.repository.CrudRepository;

public interface IGuardianRepository extends CrudRepository<Guardian,Long>  {
}
