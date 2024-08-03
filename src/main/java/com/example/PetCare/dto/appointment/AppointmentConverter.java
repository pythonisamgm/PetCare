package com.example.PetCare.dto.appointment;
import com.example.PetCare.models.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class AppointmentConverter {

    @Autowired
    private ModelMapper modelMapper;
    public AppointmentDTO appointmentToDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDTO.class);     }

    public Appointment dtoToAppointment(AppointmentDTO appointmentDTO) {
        return modelMapper.map(appointmentDTO, Appointment.class);     }

    public AppointmentTypeDTO typeToDto(Appointment type){
        return modelMapper.map(type, AppointmentTypeDTO.class);
    }
    public Appointment dtoToType(AppointmentTypeDTO typeDTO){
        return modelMapper.map(typeDTO, Appointment.class);
    }
    public PostAppointmentDTO appointmentToPostDTO(Appointment appointment){
        return modelMapper.map(appointment, PostAppointmentDTO.class);
    }
    public Appointment postDtoToAppointment(PostAppointmentDTO postAppointmentDTO){
        return modelMapper.map(postAppointmentDTO, Appointment.class);
    }
}
