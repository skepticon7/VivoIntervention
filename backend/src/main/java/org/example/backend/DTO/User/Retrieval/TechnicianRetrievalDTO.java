package org.example.backend.DTO.User.Retrieval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder @Data @AllArgsConstructor @NoArgsConstructor
public class TechnicianRetrievalDTO extends UserRetrievalDTO {
    private String site;
    private String supervisor;
    private String speciality;
    private String technicianStatus;
    private Integer completedIntervention;
}
