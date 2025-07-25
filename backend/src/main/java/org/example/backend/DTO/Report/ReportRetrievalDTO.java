package org.example.backend.DTO.Report;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder @AllArgsConstructor @NoArgsConstructor
public class ReportRetrievalDTO {
    private Integer id;
    private String pdfName;
    private String pdfLink;
    private List<Integer> interventions;
    private List<Integer> sites;
    private List<Integer> supervisorsTechnicians;
    private List<Integer> interventionTypes;
    private Integer createdById;
    private String createdByRole;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
