package org.example.backend.DTO.InterventionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class InterventionTypeInsertionDTO {
    private String code;
    private String name;
    private String description;
    private String interventionTypePriority;
    private Integer createdBySuperuserId;
}
