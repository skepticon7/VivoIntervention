package org.example.backend.Mapper.Intervention;

import org.example.backend.DTO.Intervention.InterventionInsertionDTO;
import org.example.backend.DTO.Intervention.InterventionRetrievalDTO;
import org.example.backend.DTO.Site.SiteMinimalDTO;
import org.example.backend.DTO.User.Retrieval.UserMinimalDTO;
import org.example.backend.Entities.Exportation;
import org.example.backend.Entities.Intervention;
import org.example.backend.Enums.InterventionPriority;
import org.example.backend.Enums.InterventionStatus;
import org.example.backend.Enums.Type;
import org.example.backend.Mapper.InterventionType.InterventionTypeDtoMapper;

import java.util.ArrayList;

public class InterventionDtoMapper {

    public static Intervention toEntity(InterventionInsertionDTO interventionInsertionDTO) {
        if(interventionInsertionDTO == null) return null;
        return Intervention.builder()
                .type(Type.valueOf(interventionInsertionDTO.getType()))
                .comment(interventionInsertionDTO.getComment())
                .interventionPriority(InterventionPriority.valueOf(interventionInsertionDTO.getInterventionPriority()))
                .startTime(interventionInsertionDTO.getStartTime())
                .endTime(interventionInsertionDTO.getEndTime())
                .interventionStatus(InterventionStatus.valueOf(interventionInsertionDTO.getInterventionStatus()))
                .build();
    }


    public static InterventionRetrievalDTO toDto(Intervention intervention) {
        if(intervention == null) return null;
        return InterventionRetrievalDTO.builder()
                .id(intervention.getId())
                .type(intervention.getType().toString())
                .code(intervention.getCode())
                .comment(intervention.getComment())
                .createdAt(intervention.getCreatedAt())
                .updatedAt(intervention.getUpdatedAt())
                .startDate(intervention.getStartTime())
                .endDate(intervention.getEndTime())
                .interventionStatus(intervention.getInterventionStatus().name())
                .interventionType(InterventionTypeDtoMapper.toDto(intervention.getInterventionType()))
                .interventionPriority(intervention.getInterventionPriority().name())
                .site(SiteMinimalDTO.builder()
                        .id(intervention.getSite().getId())
                        .name(intervention.getSite().getSiteName())
                        .build()
                )
                .interventionCreatedBySuperuser(intervention.getCreatedBySuperuser() != null ? intervention.getId() : null)
                .interventionCreatedBySupervisorTechnician(intervention.getCreatedBySupervisor_technician() != null ? intervention.getCreatedBySupervisor_technician().getId() : null)
                .interventionAssignedTo(intervention.getAssignedTo() != null ? (UserMinimalDTO.builder()
                        .id(intervention.getAssignedTo().getId())
                        .fullName(intervention.getAssignedTo().getFirstName().concat(" ").concat(intervention.getAssignedTo().getLastName()))
                        .build()) : null)
                .build();
    }

}
