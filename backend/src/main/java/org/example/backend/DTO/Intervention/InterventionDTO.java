package org.example.backend.DTO.Intervention;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.DTO.InterventionType.InterventionTypeRetrievalDTO;
import org.example.backend.DTO.Site.SiteRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.UserRetrievalDTO;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class InterventionDTO {
    private Integer id;
    private String type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String interventionStatus;
    private InterventionTypeRetrievalDTO interventionType;
    private String interventionPriority;
    private SiteRetrievalDTO site;
    private String comment;
    private UserRetrievalDTO interventionAssignedTo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
