package org.example.backend.DTO.Intervention;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class InterventionsChartStats {
    private String month;
    private Integer completed;
    private Integer in_progress;
    private Integer scheduled;
}
