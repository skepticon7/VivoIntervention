package org.example.backend.DTO.User.Retrieval;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class TechnicianStatsDTO {
    private Integer activeSites;
    private Integer completedInterventions;
    private Integer assignedInterventions;
}
