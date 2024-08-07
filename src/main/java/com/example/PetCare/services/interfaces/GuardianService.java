package com.example.PetCare.services.interfaces;

import com.example.PetCare.models.Guardian;
import com.example.PetCare.repositories.IGuardianRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface GuardianService {
    public Optional<Guardian> getGuardianById(Long id);
    public ArrayList<Guardian> getAllGuardians();
    public Guardian updateGuardian(Long id, Guardian newGuardian) throws Exception;
    public void deleteGuardian(Long id);
    public Guardian getGuardianByEmail(String email);
    public Optional<Guardian> getGuardianByName(String guardianName);
}
