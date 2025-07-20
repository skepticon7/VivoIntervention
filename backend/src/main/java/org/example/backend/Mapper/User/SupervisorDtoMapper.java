package org.example.backend.Mapper.User;

import org.example.backend.DTO.Site.SiteRetrievalDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.SupervisorRetrievalDTO;
import org.example.backend.Entities.*;
import org.example.backend.Enums.TechnicianStatus;

import java.util.ArrayList;

public class SupervisorDtoMapper {
    public static Supervisor toEntity(UserInsertionDTO userInsertionDTO){
        if(userInsertionDTO == null) return null;
        return Supervisor.builder()
                .firstName(userInsertionDTO.getFirstName())
                .lastName(userInsertionDTO.getLastName())
                .email(userInsertionDTO.getEmail())
                .phoneNumber(userInsertionDTO.getPhoneNumber())
                .password(userInsertionDTO.getPassword())
                .interventionsAssigned(new ArrayList<>())
                .interventionsCreated(new ArrayList<>())
                .interventionTypes(new ArrayList<>())
                .reportsConcerned(new ArrayList<>())
                .exportationsConcerned(new ArrayList<>())
                .reportsCreated(new ArrayList<>())
                .exportationsCreated(new ArrayList<>())
                .technicians(new ArrayList<>())
                .sites(new ArrayList<>())
                .technicianStatus(TechnicianStatus.valueOf(userInsertionDTO.getTechnicianStatus()))
                .hireDate(userInsertionDTO.getHireDate())
                .build();
    }

    public static SupervisorRetrievalDTO toDto(Supervisor supervisor) {
        if(supervisor == null)
            return null;
        return SupervisorRetrievalDTO.builder()
                    .id(supervisor.getId())
                    .firstName(supervisor.getFirstName())
                    .lastName(supervisor.getLastName())
                    .email(supervisor.getEmail())
                    .phoneNumber(supervisor.getPhoneNumber())
                    .createdAt(supervisor.getCreatedAt())
                    .updatedAt(supervisor.getUpdatedAt())
                    .technicianStatus(supervisor.getTechnicianStatus().name())
                    .hireDate(supervisor.getHireDate())
                    .createdBy(supervisor.getCreatedBySuperuser() != null ? supervisor.getCreatedBySuperuser().getId() : supervisor.getCreatedBySupervisor().getId())
                    .role("SUPERVISOR")
                    .sitesIds(supervisor.getSites().stream().map(Site::getId).toList())
                    .techniciansIds(supervisor.getTechnicians().stream().map(Technician::getId).toList())
                    .technicianStatus(supervisor.getTechnicianStatus().name())
                    .reportsCreated(supervisor.getReportsCreated().stream().map(Report::getId).toList())
                    .exportationsCreated(supervisor.getExportationsCreated().stream().map(Exportation::getId).toList())
                    .exportationsConcerned(supervisor.getExportationsConcerned().stream().map(Exportation::getId).toList())
                    .reportsConcerned(supervisor.getReportsConcerned().stream().map(Report::getId).toList())
                    .interventionsCreated(supervisor.getInterventionsCreated().stream().map(Intervention::getId).toList())
                    .interventionsAssigned(supervisor.getInterventionsAssigned().stream().map(Intervention::getId).toList())
                    .interventionTypesIds(supervisor.getInterventionTypes().stream().map(InterventionType::getId).toList())
                    .build();
    }
}
