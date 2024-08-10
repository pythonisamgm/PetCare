package com.example.PetCare.dto.guardian;


import com.example.PetCare.dto.pet.PetDTO;
import com.example.PetCare.models.Guardian;
import com.example.PetCare.models.Pet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GuardianConverter {

    @Autowired
    private ModelMapper modelMapper;

    public GuardianDTO guardianToDto(Guardian guardian){
        GuardianDTO guardianDTO = modelMapper.map(guardian, GuardianDTO.class);

        if (guardian.getPet() !=null){
            PetDTO petDTO = modelMapper.map(guardian.getPet(), PetDTO.class);
            guardianDTO.setPet(petDTO);

        }
    return guardianDTO;
    }
    public Guardian dtoToGuardian(GuardianDTO guardianDTO){
        Guardian guardian = modelMapper.map(guardianDTO, Guardian.class);

        if (guardianDTO.getPet() != null){
            guardian.setPet(modelMapper.map(guardianDTO.getPet(), Pet.class));

        } return guardian;
    }
    public PostGuardianDTO guardianToPostDTO(Guardian guardian){
        return modelMapper.map(guardian, PostGuardianDTO.class);
    }
    public Guardian postDtoToGuardian(PostGuardianDTO postGuardianDTO){
        return modelMapper.map(postGuardianDTO, Guardian.class);
    }
    public GuardianContactDetailsDTO guardianDetails(Guardian guardian){
        return modelMapper.map(guardian, GuardianContactDetailsDTO.class);
    }
    public Guardian guardianDetailsToDto (GuardianContactDetailsDTO guardianDetails){
        return modelMapper.map(guardianDetails, Guardian.class);
    }

}
