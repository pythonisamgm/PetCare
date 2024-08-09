package com.example.PetCare.services;

import com.example.PetCare.models.Guardian;
import com.example.PetCare.repositories.IGuardianRepository;
import com.example.PetCare.services.interfaces.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GuardianServiceImpl implements GuardianService {

    @Autowired
    IGuardianRepository iGuardianRepository;


    public Guardian createGuardian(Guardian guardian) {
        return iGuardianRepository.save(guardian);
    }
    @Override
    public Optional<Guardian> getGuardianById(Long id) {
        return iGuardianRepository.findById(id);
    }
    @Override
    public ArrayList<Guardian> getAllGuardians() {
        return (ArrayList<Guardian>) iGuardianRepository.findAll();
    }
    @Override
    public Guardian updateGuardian(Long id, Guardian newGuardian) throws Exception {
        Optional<Guardian> optionalOldGuardian = iGuardianRepository.findById(id);
        if (!optionalOldGuardian.isPresent()) {
            throw new Exception("Guardian not found");
        }
        Guardian oldGuardian = optionalOldGuardian.get();
        oldGuardian.setGuardianName(newGuardian.getGuardianName());
        oldGuardian.setTelephoneNumber(newGuardian.getTelephoneNumber());

        return iGuardianRepository.save(oldGuardian);
    }

    @Override
    public void deleteGuardian(Long id) {
        iGuardianRepository.deleteById(id);
    }



    @Override
    public Optional<Guardian> getGuardianByName(String guardianName) {
        return iGuardianRepository.getGuardianByName(guardianName);
    }

    @Override
    public Optional<Guardian> getGuardianByMail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<Guardian> getGuardianByEmail(String email) {
        return iGuardianRepository.getGuardianByEmail(email);
    }
}
