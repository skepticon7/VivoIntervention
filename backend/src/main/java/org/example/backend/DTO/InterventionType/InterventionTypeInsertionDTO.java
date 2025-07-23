package org.example.backend.DTO.InterventionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class InterventionTypeInsertionDTO {
    private String interventionName;
    private String description;
    private Integer createdBySuperuserId;
}
