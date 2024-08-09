package com.example.PetCare.repositories;

import com.example.PetCare.models.Guardian;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IGuardianRepository extends CrudRepository<Guardian,Long>  {
Optional<Guardian> getGuardianByEmail(String email);
Optional<Guardian> getGuardianByName(String guardianName);
}
