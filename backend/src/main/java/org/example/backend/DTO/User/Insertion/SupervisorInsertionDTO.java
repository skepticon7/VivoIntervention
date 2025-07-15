package org.example.backend.DTO.User.Insertion;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class SupervisorInsertionDTO extends UserInsertionDTO {
    private Integer mainSite;
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String speciality;
    private String technicianStatus;
    private Integer createdBy;
}
