package org.example.backend.DTO.Intervention;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class InterventionDashboard {
    private Integer id;
    private String code;
    private String status;
    private String type;
    private String site;
    private String priority;
    private String technician;
    private LocalDateTime startTime;
}
