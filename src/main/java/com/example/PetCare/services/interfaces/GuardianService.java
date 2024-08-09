package com.example.PetCare.services.interfaces;

import com.example.PetCare.models.Guardian;

import java.util.ArrayList;
import java.util.Optional;

public interface GuardianService {
    public Guardian createGuardian(Guardian guardian) throws Exception;
    public Optional<Guardian> getGuardianById(Long id);
    public ArrayList<Guardian> getAllGuardians();
    public Guardian updateGuardian(Long id, Guardian newGuardian) throws Exception;
    public void deleteGuardian(Long id);
    public Optional<Guardian> getGuardianByName(String guardianName);
    public Optional<Guardian> getGuardianByMail(String email);
}
