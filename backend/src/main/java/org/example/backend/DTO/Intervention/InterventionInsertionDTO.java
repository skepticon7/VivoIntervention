package org.example.backend.DTO.Intervention;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.Utils.OnCreate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class InterventionInsertionDTO {
    @NotNull(message = "Start date cannot be null" , groups = {OnCreate.class})
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @NotNull(message = "Start date cannot be null" , groups = {OnCreate.class})
    private Integer site;
    @NotBlank(message = "Intervention code cannot be blank" , groups = {OnCreate.class})
    private String code;
    @NotBlank(message = "Intervention status cannot be blank" , groups = {OnCreate.class})
    private String interventionStatus;
    @NotNull(message = "Intervention type cannot be null" , groups = {OnCreate.class})
    private Integer interventionType;
    @NotBlank(message = "Intervention priority cannot be blank" , groups = {OnCreate.class})
    private String interventionPriority;
    @NotNull(message = "Intervention description cannot be blank" , groups = {OnCreate.class})
    private Integer CreatedBy;
    @NotNull(message = "Assigned user cannot be null" , groups = {OnCreate.class})
    private Integer AssignedTo;
    private String comment;
    @NotNull(message = "Intervention date cannot be null" , groups = {OnCreate.class})
    private Boolean isSuperUser;
}
