package com.example.PetCare.services.interfaces;

import com.example.PetCare.dto.guardian.GuardianDTO;
import com.example.PetCare.dto.guardian.PostGuardianDTO;
import com.example.PetCare.models.Guardian;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface GuardianService {
    public GuardianDTO createGuardian(PostGuardianDTO postGuardianDTO) throws Exception;
    public GuardianDTO getGuardianById(Long id);
    public List<GuardianDTO> getAllGuardians();
    public GuardianDTO updateGuardian(Long id, GuardianDTO newGuardianDTO) throws Exception;
    public void deleteGuardian(Long id);
    public GuardianDTO getGuardianByName(String guardianName);
    public GuardianDTO getGuardianByMail(String email);
}
