package org.example.backend.DTO.Intervention;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class InterventionRetrievalDTO {
    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String interventionStatus;
    private String interventionType;
    private String interventionPriority;
    private String site;
    private String comment;
    private UserInsertionDTO interventionCreatedBy;
    private UserInsertionDTO interventionAssignedTo;
}
