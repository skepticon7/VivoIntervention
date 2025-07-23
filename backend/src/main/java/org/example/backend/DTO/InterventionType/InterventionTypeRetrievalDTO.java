package org.example.backend.DTO.InterventionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class InterventionTypeRetrievalDTO {
    private Integer id;
    private String interventionName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
