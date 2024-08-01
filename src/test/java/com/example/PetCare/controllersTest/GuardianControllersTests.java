package com.example.PetCare.controllersTest;


import com.example.PetCare.controllers.GuardianController;
import com.example.PetCare.services.GuardianService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.example.PetCare.models.Guardian;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.PetCare.models.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.hamcrest.Matchers.is;

class GuardianControllersTests {
    @ExtendWith(MockitoExtension.class)

    private MockMvc mockMvc;

    @Mock
    private GuardianService guardianService;

    @InjectMocks
    private GuardianController guardianController;

    public GuardianControllersTests() {
        openMocks(this);
    }
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(guardianController).build();
    }

    @Test
    void deleteGuardian_shouldDeleteGuardian() throws Exception {
        mockMvc.perform(delete("/api/guardian/1"))
                .andExpect(status().isOk());

        verify(guardianService, times(1)).deleteGuardian(1L);
    }


    @Test
    public void testCreateGuardian() throws Exception {
        Guardian guardian = new Guardian();
        guardian.setGuardianId(1L);
        guardian.setGuardianName("John Doe");
        guardian.setTelephoneNumber(123456789);

        when(guardianService.createGuardian(any(Guardian.class))).thenReturn(guardian);

        String guardianJson = "{\"nameAndSurname\":\"John Doe\", \"telephoneNumber\":123456789, \"pet\":{\"idPet\":1}}";

        mockMvc.perform(post("/guardian/guardians")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(guardianJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idGuardian").value(1L))
                .andExpect(jsonPath("$.nameAndSurname").value("John Doe"))
                .andExpect(jsonPath("$.telephoneNumber").value(123456789));
    }
    @Test
    public void testGetByGuardiansById() {
        Guardian guardian = new Guardian(1L, "John Doe", 123456789, null);
        when(guardianService.getByGuardiansById(1L)).thenReturn(Optional.of(guardian));

        ResponseEntity<Guardian> response = guardianController.getByGuardiansById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("John Doe", response.getBody().getGuardianName());
    }

    @Test
    public void testGetAllGuardians() {
        ArrayList<Guardian> guardians = new ArrayList<>();
        guardians.add(new Guardian(1L, "John Doe", 123456789, null));
        when(guardianService.getAllGuardians()).thenReturn(guardians);

        ArrayList<Guardian> result = guardianController.getAllGuardians();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getGuardianName());
    }
    @Test
    void test_if_updateGuardian_updates_information() throws Exception {
        // Arrange
        Long id = 1L;
        Pet bolita = new Pet();
        Guardian oldGuardian = new Guardian(id, "Joselitro García", 123456789, bolita);
        Guardian newGuardian = new Guardian(id, "Kid A", 987654321, bolita);

        when(guardianService.updateGuardian(anyLong(), any(Guardian.class))).thenReturn(newGuardian);

        ObjectMapper objectMapper = new ObjectMapper();
        String newGuardianJson = objectMapper.writeValueAsString(newGuardian);

        // Act & Assert
        mockMvc.perform(put("/guardian/guardians/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newGuardianJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameAndSurname", is("Kid A")))
                .andExpect(jsonPath("$.telephoneNumber", is(987654321)));
    }
}
