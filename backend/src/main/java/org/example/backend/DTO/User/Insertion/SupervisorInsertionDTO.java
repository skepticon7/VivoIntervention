package org.example.backend.DTO.User.Insertion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class SupervisorInsertionDTO {
    private Integer mainSite;
    private String speciality;
    private String technicianStatus;
    private Integer createdBy;
}
