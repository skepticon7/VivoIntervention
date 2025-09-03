package org.example.backend.DTO.User.Retrieval;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class SupervisorSuperuserStatsDTO {
    private Integer completedInterventions;
    private Integer activeSites;
    private Integer availableUsers;
}
