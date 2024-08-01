package com.example.PetCare.services;

import com.example.PetCare.models.Guardian;
import com.example.PetCare.repositories.IGuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GuardianService {

    @Autowired
    IGuardianRepository iGuardianRepository;

    public Guardian createGuardian(Guardian guardian) {
        return iGuardianRepository.save(guardian);
    }

    @Autowired
    private IGuardianRepository guardianRepository;
    public Optional<Guardian> getByGuardiansById(Long id) { return iGuardianRepository.findById(id); }

    public ArrayList<Guardian> getAllGuardians() { return (ArrayList<Guardian>) iGuardianRepository.findAll(); }
    public Guardian updateGuardian(Long id, Guardian newGuardian) {
        Optional<Guardian> optionalOldGuardian = iGuardianRepository.findById(id);

        Guardian oldGuardian = optionalOldGuardian.get();
        oldGuardian.setGuardianName(newGuardian.getGuardianName());
        oldGuardian.setTelephoneNumber(newGuardian.getTelephoneNumber());

        return iGuardianRepository.save(oldGuardian);
    }

    public void deleteGuardian(Long id) {
        iGuardianRepository.deleteById(id);
    }
}
