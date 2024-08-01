package com.example.PetCare.controllersTest;

import com.example.PetCare.controllers.PetController;
import com.example.PetCare.models.Appointment;
import com.example.PetCare.models.Guardian;
import com.example.PetCare.models.Pet;
import com.example.PetCare.services.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.PetCare.exceptions.PetNotFoundException;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetController.class)

public class PetControllersTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;
    private PetController petController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(petController)
                .build();
    }

    @Test
    public void deletePetTest() throws Exception {
        Long petId = 1L;


        doNothing().when(petService).deletePet(petId);


        mockMvc.perform(delete("/pet/pets/{id}", petId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        verify(petService).deletePet(petId);
    }
    @Test
    public void testAddNewPet() throws Exception {
        Pet pet = new Pet();
        pet.setPetId(1L);
        pet.setPetName("Balud");
        pet.setAge(5);
        pet.setBreed("Golden Retriever");
        pet.setGender("Male");
        pet.setUrl("http://example.com/balud.jpg");

        when(petService.addNewPet(any(Pet.class))).thenReturn(pet);

        ObjectMapper objectMapper = new ObjectMapper();
        String petJson = objectMapper.writeValueAsString(pet);

        ResultActions resultActions = mockMvc.perform(post("/pet/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(petJson));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.idPet").value(1L))
                .andExpect(jsonPath("$.name").value("Balud"))
                .andExpect(jsonPath("$.age").value(5))
                .andExpect(jsonPath("$.race").value("Golden Retriever"))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.url").value("http://example.com/balud.jpg"));
    }
    @Test
    void listPet_shouldReturnListOfPets() {
        List<Guardian> guardianList = new ArrayList<>();
        List<Appointment> appointmentList = new ArrayList<>();
        Pet pet1 = new Pet(1L, "Gordita", 12, "Westie", "Female", "dog", "url", guardianList, appointmentList);
        Pet pet2 = new Pet(2L, "Shazam", 10, "Pitbull", "Female","dog", "url", guardianList, appointmentList);
        when(petService.listPet()).thenReturn(Arrays.asList(pet1, pet2));

        List<Pet> result = petController.listPet();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(petService, times(1)).listPet();
    }

    @Test
    void updatePet_shouldReturnUpdatedPet() {
        List<Guardian> guardianList = new ArrayList<>();
        List<Appointment> appointmentList = new ArrayList<>();
        Pet existingPet = new Pet(1L, "Gordita", 12, "Westie", "Female", "dog","url", guardianList, appointmentList);
        Pet updatedPetDetails = new Pet(1L, "Gordita Updated", 13, "Westie", "Female", "dog","url", guardianList, appointmentList);

        when(petService.getPetById(1L)).thenReturn(Optional.of(existingPet));
        when(petService.updatePet(existingPet)).thenReturn(updatedPetDetails);

        Pet result = petController.updatePet(1L, updatedPetDetails);

        assertEquals("Gordita Updated", result.getPetName());
        assertEquals(13, result.getAge());
        verify(petService, times(1)).getPetById(1L);
        verify(petService, times(1)).updatePet(existingPet);
    }

    @Test
    void updatePet_whenPetNotFound_shouldThrowException() {
        List<Guardian> guardianList = new ArrayList<>();
        List<Appointment> appointmentList = new ArrayList<>();
        Pet updatedPetDetails = new Pet(1L, "Gordita Updated", 13, "Westie","dog", "Female", "url", guardianList, appointmentList);

        when(petService.getPetById(1L)).thenReturn(Optional.empty());

        assertThrows(PetNotFoundException.class, () -> {
            petController.updatePet(1L, updatedPetDetails);
        });

        verify(petService, times(1)).getPetById(1L);
    }
}


