package com.example.PetCare.services;

import com.example.PetCare.dto.guardian.GuardianConverter;
import com.example.PetCare.dto.guardian.GuardianDTO;
import com.example.PetCare.dto.guardian.PostGuardianDTO;
import com.example.PetCare.models.Guardian;
import com.example.PetCare.repositories.IGuardianRepository;
import com.example.PetCare.services.interfaces.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GuardianServiceImpl implements GuardianService {

    @Autowired
    IGuardianRepository iGuardianRepository;

    @Autowired
    GuardianConverter guardianConverter;

    @Override
    public GuardianDTO createGuardian(PostGuardianDTO postGuardianDTO) {
        Guardian guardian = guardianConverter.postDtoToGuardian(postGuardianDTO);
        Guardian savedGuardian = iGuardianRepository.save(guardian);

        return guardianConverter.guardianToDto(savedGuardian);
    }
    @Override
    public Optional<GuardianDTO> getGuardianById(Long id) {
        Optional<Guardian> guardian = iGuardianRepository.findById(id);

        return guardian.map(guardianConverter::guardianToDto);
    }

    @Override
    public List<GuardianDTO> getAllGuardians() {
        List<Guardian> guardians = (List<Guardian>) iGuardianRepository.findAll();

        return guardians.stream()
                .map(guardianConverter::guardianToDto)
                .collect(Collectors.toList());
    }
    @Override
    public GuardianDTO updateGuardian(Long id, GuardianDTO newGuardianDTO) throws Exception {
        Optional<Guardian> optionalOldGuardian = iGuardianRepository.findById(id);
        if (!optionalOldGuardian.isPresent()) {
            throw new Exception("Guardian not found");
        }
        Guardian oldGuardian = optionalOldGuardian.get();
        oldGuardian.setGuardianName(newGuardianDTO.getGuardianName());
        oldGuardian.setTelephoneNumber(newGuardianDTO.getTelephoneNumber());
        Guardian updatedGuardian = iGuardianRepository.save(oldGuardian);
        return guardianConverter.guardianToDto(updatedGuardian);
    }

    @Override
    public void deleteGuardian(Long id) {
        iGuardianRepository.deleteById(id);
    }



    @Override
    public GuardianDTO getGuardianByName(String guardianName) {
        Guardian guardian = iGuardianRepository.getGuardianByName(guardianName);
        return guardianConverter.guardianToDto(guardian);
    }

    @Override
    public GuardianDTO getGuardianByMail(String email) {

        Guardian guardian = iGuardianRepository.getGuardianByMail(email);
        return guardianConverter.guardianToDto(guardian);
    }


}
