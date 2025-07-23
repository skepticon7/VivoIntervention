package org.example.backend.DTO.Intervention;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class InterventionRetrievalDTO {
    private Integer id;
    private String code;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String interventionStatus;
    private Integer interventionType;
    private String interventionPriority;
    private List<Integer> exportations;
    private List<Integer> reports;
    private Integer site;
    private String comment;
    private Integer interventionCreatedBySuperuser;
    private Integer interventionCreatedBySupervisorTechnician;
    private Integer interventionAssignedTo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
