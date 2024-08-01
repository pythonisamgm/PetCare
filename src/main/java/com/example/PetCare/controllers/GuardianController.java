package com.example.PetCare.controllers;

import com.example.PetCare.models.Guardian;
import com.example.PetCare.services.GuardianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/guardian")
@CrossOrigin(origins = "*")

public class GuardianController {
    @Autowired
    GuardianService guardianService;

    @PostMapping(path = "/guardians")
    public Guardian createGuardian(@RequestBody Guardian guardian) {
        return guardianService.createGuardian(guardian);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Guardian> getByGuardiansById(@PathVariable Long id) {
        Optional<Guardian> guardian = guardianService.getByGuardiansById(id);
        if (guardian.isPresent()) {
            return ResponseEntity.ok(guardian.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<Guardian> getAllGuardians() {
        return guardianService.getAllGuardians();
    }
    @PutMapping(path = "/guardians/{id}")
    public Guardian updateGuardian(@RequestBody Guardian guardian, @PathVariable Long id) {
        return guardianService.updateGuardian(id, guardian);
    }
    @DeleteMapping("/{id}")
    public void deleteGuardian(@PathVariable Long id) {
        guardianService.deleteGuardian(id);
    }

}