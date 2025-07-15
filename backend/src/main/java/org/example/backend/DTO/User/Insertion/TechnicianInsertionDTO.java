package org.example.backend.DTO.User.Insertion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class TechnicianInsertionDTO extends UserInsertionDTO{
    private String speciality;
    private String technicianStatus;
    private Integer supervisor;
    private List<Integer> assignedSites;
    private LocalDate hireDate;
    private Integer createdBy;
}
