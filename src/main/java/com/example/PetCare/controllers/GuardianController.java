package com.example.PetCare.controllers;

import com.example.PetCare.dto.appointment.AppointmentConverter;
import com.example.PetCare.dto.appointment.AppointmentDTO;
import com.example.PetCare.dto.appointment.PostAppointmentDTO;
import com.example.PetCare.dto.guardian.GuardianConverter;
import com.example.PetCare.dto.guardian.GuardianDTO;
import com.example.PetCare.dto.guardian.PostGuardianDTO;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuardianDTO createGuardian(@RequestBody PostGuardianDTO postGuardianDTO) {
        logger.info("POST /api/v1/appointments --> " + postGuardianDTO.toString());
        Guardian guardian = guardianConverter.postDtoToGuardian(postGuardianDTO);
        Guardian createdGuardian = guardianService.createGuardian(guardian);
        return guardianConverter.guardianToDto(createdGuardian);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GuardianDTO> getAllGuardians() {
        logger.info("GET /api/v1/guardians");
        List<Guardian> guardians = guardianService.getAllGuardians();
        return guardians.stream()
                .map(guardianConverter::guardianToDto)
                .collect(Collectors.toList());
    }
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<GuardianDTO> getByGuardiansById(@PathVariable Long id) {
        Optional<Guardian> guardian = guardianService.getGuardianById(id);
        return guardian.map(guardianConverter::guardianToDto);
    }

    @GetMapping(path="{name}")

    @PutMapping(path = "/{id}")
    public Guardian updateGuardian(@RequestBody Guardian guardian, @PathVariable Long id) throws Exception {
        return guardianService.updateGuardian(id, guardian);
    }
    @DeleteMapping("/{id}")
    public void deleteGuardian(@PathVariable Long id) {
        guardianService.deleteGuardian(id);
    }

}