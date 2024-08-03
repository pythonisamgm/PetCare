package com.example.PetCare.dto.pet;

import com.example.PetCare.models.Pet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

@Component
public class PetConverter {
    @Autowired
    ModelMapper modelMapper;

    public PetDTO entityToDTO(Pet pet){
        return modelMapper.map(pet, PetDTO.class);
    }
    public Pet petDtoToEntity(PetDTO petDTO){
        return modelMapper.map(petDTO, Pet.class);
    }
}
