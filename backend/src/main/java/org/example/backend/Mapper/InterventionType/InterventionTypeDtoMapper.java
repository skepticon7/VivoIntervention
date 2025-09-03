package org.example.backend.Mapper.InterventionType;

import org.example.backend.DTO.InterventionType.InterventionTypeInsertionDTO;
import org.example.backend.DTO.InterventionType.InterventionTypeRetrievalDTO;
import org.example.backend.Entities.InterventionType;
import org.example.backend.Enums.InterventionPriority;
import org.example.backend.Enums.InterventionStatus;

import java.util.ArrayList;

public class InterventionTypeDtoMapper {


    public static InterventionType toEntity(InterventionTypeInsertionDTO dto) {
        if(dto == null) return null;
        return InterventionType.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .interventionTypePriority(InterventionPriority.valueOf(dto.getInterventionTypePriority()))
                .interventions(new ArrayList<>())
                .build();
    }


    public static InterventionTypeRetrievalDTO toDto(InterventionType entity) {
        if(entity == null) return null;
        return InterventionTypeRetrievalDTO.builder()
                .id(entity.getId())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .name(entity.getName())
                .description(entity.getDescription())
                .interventionTypePriority(entity.getInterventionTypePriority().name())
                .interventionsCompleted((int) entity.getInterventions().stream().filter(inter -> inter.getInterventionStatus() == InterventionStatus.COMPLETED).count())
                .interventionsAssigned(entity.getInterventions().size())
                .build();

    }
}
