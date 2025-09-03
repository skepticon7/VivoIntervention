package org.example.backend.DTO.Intervention;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder @NoArgsConstructor @AllArgsConstructor @Data
public class InterventionsStats {
    private Integer total;
    private Integer inProgress;
    private Integer completed;
    private Integer urgent;
}
