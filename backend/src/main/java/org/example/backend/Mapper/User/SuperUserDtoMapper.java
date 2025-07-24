package org.example.backend.Mapper.User;

import org.example.backend.DTO.SuperUser.SuperUserInsertionDTO;
import org.example.backend.DTO.SuperUser.SuperUserRetrievalDTO;
import org.example.backend.Entities.*;

import java.util.ArrayList;

public class SuperUserDtoMapper {
    public static SuperUserRetrievalDTO toDto(SuperUser superUser) {
        if(superUser == null) return null;
        return  SuperUserRetrievalDTO.builder()
                .Id(superUser.getId())
                .firstName(superUser.getFirstName())
                .lastName(superUser.getLastName())
                .email(superUser.getEmail())
                .phoneNumber(superUser.getPhoneNumber())
                .role("SUPER USER")
                .sites(superUser.getSites().stream().map(Site::getId).toList())
                .users(superUser.getUsers().stream().map(User::getId).toList())
                .interventions(superUser.getInterventions().stream().map(Intervention::getId).toList())
                .reports(superUser.getReports().stream().map(Report::getId).toList())
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
                .phoneNumber(superUserInsertionDTO.getPhoneNumber())
                .sites(new ArrayList<>())
                .users(new ArrayList<>())
                .interventions(new ArrayList<>())
                .reports(new ArrayList<>())
                .exportations(new ArrayList<>())
                .interventionTypes(new ArrayList<>())
                .build();
    }
}
