package org.example.backend.DTO.Intervention;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class InterventionInsertionDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer site;
    private String code;
    private String interventionStatus;
    private Integer interventionType;
    private String interventionPriority;
    private Integer CreatedBy;
    private Integer AssignedTo;
    private String comment;
    private Boolean isSuperUser;
}
