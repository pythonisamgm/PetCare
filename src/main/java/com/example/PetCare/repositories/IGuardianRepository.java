package com.example.PetCare.repositories;

import com.example.PetCare.models.Guardian;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IGuardianRepository extends CrudRepository<Guardian,Long>  {
Guardian getGuardianByMail(String email);
Guardian getGuardianByName(String guardianName);
}
