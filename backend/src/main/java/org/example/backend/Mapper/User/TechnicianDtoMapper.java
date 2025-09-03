package org.example.backend.Mapper.User;

import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.TechnicianRetrievalDTO;
import org.example.backend.Entities.*;
import org.example.backend.Enums.InterventionStatus;
import org.example.backend.Enums.TechnicianStatus;
import org.example.backend.Repository.UserRepository;

import java.util.ArrayList;

public class TechnicianDtoMapper {
    public static TechnicianRetrievalDTO toDto(Technician technician){
       if(technician == null) return null;
       return TechnicianRetrievalDTO.builder()
                   .id(technician.getId())
                   .firstName(technician.getFirstName())
                   .lastName(technician.getLastName())
                   .email(technician.getEmail())
                   .phoneNumber(technician.getPhoneNumber())
                   .hireDate(technician.getHireDate())
                   .technicianStatus(technician.getTechnicianStatus().name())
                   .createdAt(technician.getCreatedAt())
                   .updatedAt(technician.getUpdatedAt())
                   .role("TECHNICIAN")
                     .interventionsCompleted((int) technician.getInterventionsAssigned().stream().filter(inter -> inter.getInterventionStatus().equals(InterventionStatus.COMPLETED)).count())
                   .interventionsAssigned(technician.getInterventionsAssigned().stream().map(Intervention::getId).toList())
                   .interventionsCreated(technician.getInterventionsCreated().stream().map(Intervention::getId).toList())
                   .createdBy(technician.getCreatedBySuperuser() == null && technician.getCreatedBySupervisor() == null  ? null : (technician.getCreatedBySupervisor() != null ? technician.getCreatedBySupervisor().getId() : technician.getCreatedBySuperuser().getId()))
                   .build();
    }


    public static Technician toEntity(TechnicianInsertionDTO technicianInsertionDTO){
         if(technicianInsertionDTO == null)
             return null;
         return Technician.builder()
                 .firstName(technicianInsertionDTO.getFirstName())
                    .lastName(technicianInsertionDTO.getLastName())
                    .email(technicianInsertionDTO.getEmail())
                    .phoneNumber(technicianInsertionDTO.getPhoneNumber())
                    .technicianStatus(TechnicianStatus.valueOf(technicianInsertionDTO.getTechnicianStatus()))
                    .interventionsAssigned(new ArrayList<>())
                    .interventionsCreated(new ArrayList<>())
                    .hireDate(technicianInsertionDTO.getHireDate())
                    .build();
    }
}
