package org.example.backend.DTO.Intervention;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.DTO.InterventionType.InterventionTypeDTO;
import org.example.backend.DTO.InterventionType.InterventionTypeRetrievalDTO;
import org.example.backend.DTO.Site.SiteMinimalDTO;
import org.example.backend.DTO.User.Retrieval.UserMinimalDTO;
import org.example.backend.Entities.InterventionType;

import java.time.LocalDateTime;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class InterventionRetrievalDTO {
    private Integer id;
    private String type;
    private String code;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String interventionStatus;
    private InterventionTypeRetrievalDTO interventionType;
    private String interventionPriority;
    private List<Integer> exportations;
    private SiteMinimalDTO site;
    private String comment;
    private Integer interventionCreatedBySuperuser;
    private Integer interventionCreatedBySupervisorTechnician;
    private UserMinimalDTO interventionAssignedTo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
