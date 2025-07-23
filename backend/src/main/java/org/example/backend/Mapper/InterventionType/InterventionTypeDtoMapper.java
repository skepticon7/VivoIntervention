package org.example.backend.Mapper.InterventionType;

import org.example.backend.DTO.InterventionType.InterventionTypeInsertionDTO;
import org.example.backend.DTO.InterventionType.InterventionTypeRetrievalDTO;
import org.example.backend.Entities.InterventionType;
import org.example.backend.Entities.SuperUser;

public class InterventionTypeDtoMapper {


    public static InterventionType toEntity(InterventionTypeInsertionDTO dto) {

        if (dto == null) {
            throw new IllegalArgumentException("InterventionTypeInsertionDTO cannot be null");
        }

        if (dto.getCreatedBySuperuserId() == null) {
            throw new IllegalArgumentException("createdBySuperuserId is required");
        }





        return InterventionType.builder()
                .interventionName(dto.getInterventionName())
                .description(dto.getDescription())
                .build();
    }


    public static InterventionTypeRetrievalDTO toDto(InterventionType entity) {

        if (entity == null) {
            throw new IllegalArgumentException("L'entité InterventionType cannot be null");
        }


        return InterventionTypeRetrievalDTO.builder()
                .id(entity.getId())
                .interventionName(entity.getInterventionName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
