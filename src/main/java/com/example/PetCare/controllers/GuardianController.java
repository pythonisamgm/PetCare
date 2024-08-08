package com.example.PetCare.controllers;

import com.example.PetCare.dto.appointment.AppointmentConverter;
import com.example.PetCare.dto.appointment.AppointmentDTO;
import com.example.PetCare.dto.appointment.PostAppointmentDTO;
import com.example.PetCare.dto.guardian.GuardianConverter;
import com.example.PetCare.dto.guardian.GuardianDTO;
import com.example.PetCare.dto.guardian.PostGuardianDTO;
import com.example.PetCare.dto.pet.PetConverter;
import com.example.PetCare.dto.pet.PetDTO;
import com.example.PetCare.models.Appointment;
import com.example.PetCare.models.Guardian;
import com.example.PetCare.services.AppointmentServiceImpl;
import com.example.PetCare.services.GuardianServiceImpl;
import com.example.PetCare.services.GuardianServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/guardians")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class GuardianController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    GuardianServiceImpl guardianService;

    @Autowired
    private GuardianConverter guardianConverter;

    @Autowired
    private PetConverter petConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuardianDTO createGuardian(@RequestBody PostGuardianDTO postGuardianDTO) {
        logger.info("POST /api/v1/guardians --> " + postGuardianDTO.toString());
        Guardian guardian = guardianConverter.postDtoToGuardian(postGuardianDTO);
        Guardian createdGuardian = guardianService.createGuardian(guardian);
        return guardianConverter.guardianToDto(createdGuardian);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GuardianDTO> getAllGuardians() {
        logger.info("GET /api/v1/guardians");
        List<Guardian> guardians = guardianService.getAllGuardians();
        return guardians.stream()
                .map(guardianConverter::guardianToDto)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<GuardianDTO> getGuardianById(@PathVariable Long id) {
        logger.info("GET /api/v1/guardians" + id);
        Optional<Guardian> guardian = guardianService.getGuardianById(id);
        return guardian.map(guardianConverter::guardianToDto);
    }

    @GetMapping(path = "{name}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<GuardianDTO> getGuardianByName(@RequestParam String guardianName) {
        logger.info("GET /api/v1/guardians/name?guardianName=" + guardianName);
        return guardianService.getGuardianByName(guardianName)
                .map(guardian -> {
                    PetDTO petDTO = petConverter.entityToDTO(guardian.getPet());
                    return GuardianDTO.builder()
                            .guardianId(guardian.getGuardianId())
                            .guardianName(guardian.getGuardianName())
                            .telephoneNumber(guardian.getTelephoneNumber())
                            .email(guardian.getEmail())
                            .sysRegistryDate(guardian.getSysRegistryDate())
                            .pet(petDTO)
                            .build();
                });
    }
    @GetMapping(path = "{email}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<GuardianDTO> getGuardianByEmail(@RequestParam String email) {
        logger.info("GET /api/v1/guardians/email?email=" + email);
        return guardianService.getGuardianByMail(email)
                .map(guardian -> {
                    PetDTO petDTO = petConverter.entityToDTO(guardian.getPet());
                    return GuardianDTO.builder()
                            .guardianId(guardian.getGuardianId())
                            .guardianName(guardian.getGuardianName())
                            .telephoneNumber(guardian.getTelephoneNumber())
                            .email(guardian.getEmail())
                            .sysRegistryDate(guardian.getSysRegistryDate())
                            .pet(petDTO)
                            .build();
                });
    }

    @PutMapping(path = "/{id}")
    public void updateGuardian(@RequestBody PostGuardianDTO postGuardianDTO, @PathVariable Long id) throws Exception {
        logger.info("PUT /api/v1/guardians/{} --> {}", id, postGuardianDTO.toString());
        Guardian request = guardianConverter.postDtoToGuardian(postGuardianDTO);
        guardianService.updateGuardian(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGuardian(@PathVariable Long id) {
        logger.info("DELETE /api/v1/guardians/{}", id);
        guardianService.deleteGuardian(id);
    }

}