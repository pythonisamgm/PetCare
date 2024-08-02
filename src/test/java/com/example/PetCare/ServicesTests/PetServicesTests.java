package com.example.PetCare.ServicesTests;

import com.example.PetCare.models.Appointment;
import com.example.PetCare.models.Guardian;
import com.example.PetCare.models.Pet;
import com.example.PetCare.repositories.IPetRepository;
import com.example.PetCare.services.PetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PetServicesTests {
    @Mock
    private IPetRepository iPetRepository;

    @InjectMocks
    private PetServiceImpl petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        List<Guardian> guardianList = new ArrayList<>();
        List<Appointment> appointmentList = new ArrayList<>();
    }

    public void PetServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeletePet(){
        Pet petToDelete = new Pet();
        Long petId = 1L;

        when(iPetRepository.existsById(petId)).thenReturn(true);

        petService.deletePet(1L);

        verify(iPetRepository, times(1)).deleteById(petId);

    }

    @Test
    void listPet_shouldReturnAllPets() {
        List<Guardian> guardianList = new ArrayList<>();
        List<Appointment> appointmentList = new ArrayList<>();
        Pet pet1 = new Pet(1L, "Gordita", 12, "Westie", "Female","dog", "url", guardianList, appointmentList);
        Pet pet2 = new Pet(2L, "Shazam", 10, "Pitbull", "Female", "dog", "url", guardianList, appointmentList);
        when(iPetRepository.findAll()).thenReturn(Arrays.asList(pet1, pet2));

        List<Pet> pets = petService.listPet();

        assertEquals(2, pets.size());
        verify(iPetRepository, times(1)).findAll();
    }

    @Test
    void getPetById_shouldReturnPet() {
        List<Guardian> guardianList = new ArrayList<>();
        List<Appointment> appointmentList = new ArrayList<>();
        Pet pet = new Pet(2L, "Shazam", 10, "Pitbull", "Female", "dog", "url", guardianList, appointmentList);
        when(iPetRepository.findById(2L)).thenReturn(Optional.of(pet));

        Optional<Pet> foundPet = petService.getPetById(2L);

        assertTrue(foundPet.isPresent());
        assertEquals("Shazam", foundPet.get().getPetName());
        verify(iPetRepository, times(1)).findById(2L);
    }

    @Test
    void updatePet_shouldReturnUpdatedPet() {
        List<Guardian> guardianList = new ArrayList<>();
        List<Appointment> appointmentList = new ArrayList<>();
        Pet pet = new Pet(3L, "Krispyn", 6, "American shorthair", "Female", "dog", "url", guardianList, appointmentList);
        when(iPetRepository.save(pet)).thenReturn(pet);

        Pet updatedPet = petService.updatePet(pet);

        assertNotNull(updatedPet);
        assertEquals("Krispyn", updatedPet.getPetName());
        verify(iPetRepository, times(1)).save(pet);
    }

    @Test
    void addPet_shouldReturnAddedPet() {
        List<Guardian> guardianList = new ArrayList<>();
        List<Appointment> appointmentList = new ArrayList<>();
        Pet pet = new Pet(4L, "Luffy", 4, "Westie", "Male", "dog", "url", guardianList, appointmentList);
        when(iPetRepository.save(pet)).thenReturn(pet);

        Pet addedPet = petService.addPet(pet);

        assertNotNull(addedPet);
        assertEquals("Luffy", addedPet.getPetName());
    }
}