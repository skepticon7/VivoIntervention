package org.example.backend.Mapper.User;

import org.example.backend.DTO.SuperUser.SuperUserInsertionDTO;
import org.example.backend.DTO.SuperUser.SuperUserRetrievalDTO;
import org.example.backend.Entities.*;
import org.example.backend.Enums.TechnicianStatus;

import java.util.ArrayList;

public class SuperUserDtoMapper {
    public static SuperUserRetrievalDTO toDto(SuperUser superUser) {
        if(superUser == null) return null;
        return  SuperUserRetrievalDTO.builder()
                .Id(superUser.getId())
                .firstName(superUser.getFirstName())
                .lastName(superUser.getLastName())
                .hireDate(superUser.getHireDate())
                .email(superUser.getEmail())
                .phoneNumber(superUser.getPhoneNumber())
                .technicianStatus(superUser.getTechnicianStatus().name())
                .role("SUPER USER")
                .sites(superUser.getSites().stream().map(Site::getId).toList())
                .users(superUser.getUsers().stream().map(User::getId).toList())
                .interventions(superUser.getInterventions().stream().map(Intervention::getId).toList())
                .exportations(superUser.getExportations().stream().map(Exportation::getId).toList())
                .interventionTypes(superUser.getInterventionTypes().stream().map(InterventionType::getId).toList())
                .createdAt(superUser.getUpdatedAt())
                .updatedAt(superUser.getUpdatedAt())
                .build();
    }

    public static SuperUser toEntity(SuperUserInsertionDTO superUserInsertionDTO){
        if(superUserInsertionDTO == null) return null;
        return SuperUser.builder()
                .firstName(superUserInsertionDTO.getFirstName())
                .lastName(superUserInsertionDTO.getLastName())
                .email(superUserInsertionDTO.getEmail())
                .hireDate(superUserInsertionDTO.getHireDate())
                .technicianStatus(TechnicianStatus.valueOf(superUserInsertionDTO.getTechnicianStatus()))
                .phoneNumber(superUserInsertionDTO.getPhoneNumber())
                .sites(new ArrayList<>())
                .users(new ArrayList<>())
                .interventions(new ArrayList<>())
                .exportations(new ArrayList<>())
                .interventionTypes(new ArrayList<>())
                .build();
    }
}
