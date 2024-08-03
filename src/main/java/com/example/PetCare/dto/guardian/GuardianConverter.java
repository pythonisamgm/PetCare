package com.example.PetCare.dto.guardian;

import com.example.PetCare.models.Guardian;
import org.hibernate.annotations.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuardianConverter {

    @Autowired
    private ModelMapper modelMapper;

    public GuardianDTO guardianToDto(Guardian guardian){
        return modelMapper.map(guardian, GuardianDTO.class);
    }
    public Guardian dtoToGuardian(GuardianDTO guardianDTO){
        return modelMapper.map(guardianDTO, Guardian.class);
    }

}
