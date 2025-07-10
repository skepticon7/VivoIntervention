package org.example.backend.Mapper.User;

import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Retrieval.TechnicianRetrievalDTO;
import org.example.backend.Entities.Technician;
import org.example.backend.Enums.TechnicianStatus;
import org.example.backend.Repository.UserRepository;

public class TechnicianDtoMapper {
    public static TechnicianRetrievalDTO toDto(Technician technician){
       if(technician == null) return null;
       return TechnicianRetrievalDTO.builder()
               .id(technician.getId())
               .fullName(technician.getFullName())
               .email(technician.getEmail())
               .role(technician.getRole())
               .createdAt(technician.getCreatedAt())
               .updatedAt(technician.getUpdatedAt())
               .phoneNumber(technician.getPhoneNumber())
               .site(technician.getMainSite().getSiteName())
               .technicianStatus(technician.getTechnicianStatus().toString())
               .completedIntervention((int) technician.getAssignedInterventions().stream().filter(inter -> inter.getInterventionStatus().toString().equalsIgnoreCase("COMPLETED")).count())
               .supervisor(technician.getSupervisor().getFullName())
               .speciality(technician.getSpeciality())
               .build();
    }


    public static Technician toEntity(TechnicianInsertionDTO technicianInsertionDTO){
        if(technicianInsertionDTO == null) return null;
        return Technician.builder()
                .fullName(technicianInsertionDTO.getFullName())
                .email(technicianInsertionDTO.getEmail())
                .phoneNumber(technicianInsertionDTO.getPhoneNumber())
                .speciality(technicianInsertionDTO.getSpeciality())
                .technicianStatus(TechnicianStatus.valueOf(technicianInsertionDTO.getTechnicianStatus().toUpperCase()))
                .build();
    }
}
