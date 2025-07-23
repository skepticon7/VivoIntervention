package org.example.backend.DTO.Exportation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.List;

@Builder @NoArgsConstructor @AllArgsConstructor @Data
public class ExportationInsertionDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private String fileName;
    private String fileLink;
    private List<Integer> supervisors_technicians;
    private List<Integer> interventions;
    private List<Integer> sites;
    private Integer createdBy;
    private Boolean isSuperuser; // true if created by a superuser, false if created by a supervisor
}
