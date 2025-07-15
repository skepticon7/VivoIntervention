package org.example.backend.DTO.User.Retrieval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.backend.DTO.Site.SiteRetrievalDTO;

import java.util.List;

@Data @SuperBuilder @AllArgsConstructor @NoArgsConstructor
public class SupervisorRetrievalDTO extends UserRetrievalDTO{
    private SiteRetrievalDTO site;
    private List<String> specialities;
    private String technicianStatus;
    private Integer teamSize;
    private Integer completedIntervention;
}
