package org.example.backend.Mapper.Intervention;

import org.example.backend.DTO.Intervention.InterventionInsertionDTO;
import org.example.backend.DTO.Intervention.InterventionRetrievalDTO;
import org.example.backend.Entities.Exportation;
import org.example.backend.Entities.Intervention;
import org.example.backend.Entities.Report;
import org.example.backend.Enums.InterventionPriority;
import org.example.backend.Enums.InterventionStatus;

import java.util.ArrayList;

public class InterventionDtoMapper {

    public static Intervention toEntity(InterventionInsertionDTO interventionInsertionDTO) {
        if(interventionInsertionDTO == null) return null;
        return Intervention.builder()
                .code(interventionInsertionDTO.getCode())
                .comment(interventionInsertionDTO.getComment())
                .interventionPriority(InterventionPriority.valueOf(interventionInsertionDTO.getInterventionPriority()))
                .startTime(interventionInsertionDTO.getStartTime())
                .endTime(interventionInsertionDTO.getEndTime())
                .interventionStatus(InterventionStatus.valueOf(interventionInsertionDTO.getInterventionStatus()))
                .reports(new ArrayList<>())
                .exportations(new ArrayList<>())
                .build();
    }


    public static InterventionRetrievalDTO toDto(Intervention intervention) {
        if(intervention == null) return null;
        return InterventionRetrievalDTO.builder()
                .id(intervention.getId())
                .code(intervention.getCode())
                .comment(intervention.getComment())
                .createdAt(intervention.getCreatedAt())
                .updatedAt(intervention.getUpdatedAt())
                .startDate(intervention.getStartTime())
                .endDate(intervention.getEndTime())
                .interventionStatus(intervention.getInterventionStatus().name())
                .interventionType(intervention.getInterventionType().getId())
                .interventionPriority(intervention.getInterventionPriority().name())
                .reports(intervention.getReports().stream().map(Report::getId).toList())
                .exportations(intervention.getExportations().stream().map(Exportation::getId).toList())
                .site(intervention.getSite().getId())
                .interventionCreatedBySuperuser(intervention.getCreatedBySuperuser() != null ? intervention.getId() : null)
                .interventionCreatedBySupervisorTechnician(intervention.getCreatedBySupervisor_technician() != null ? intervention.getCreatedBySupervisor_technician().getId() : null)
                .interventionAssignedTo(intervention.getAssignedTo() != null ? intervention.getAssignedTo().getId() : null)
                .build();
    }

}
