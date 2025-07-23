package org.example.backend.Mapper.User;

import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.TechnicianRetrievalDTO;
import org.example.backend.Entities.*;
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
                   .interventionsAssigned(technician.getInterventionsAssigned().stream().map(Intervention::getId).toList())
                   .interventionsCreated(technician.getInterventionsCreated().stream().map(Intervention::getId).toList())
                   .reportsConcerned(technician.getReportsConcerned().stream().map(Report::getId).toList())
                   .exportationsConcerned(technician.getExportationsConcerned().stream().map(Exportation::getId).toList())
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
                    .password(technicianInsertionDTO.getPassword())
                    .technicianStatus(TechnicianStatus.valueOf(technicianInsertionDTO.getTechnicianStatus()))
                    .interventionsAssigned(new ArrayList<>())
                    .interventionsCreated(new ArrayList<>())
                    .reportsConcerned(new ArrayList<>())
                    .exportationsConcerned(new ArrayList<>())
                    .hireDate(technicianInsertionDTO.getHireDate())
                    .build();
    }
}
