package org.example.backend.DTO.Report;

import lombok.Data;

@Data
public class ReportInsertionDTO {
    private String pdfName;
    private String pdfLink;
    private Integer createdBySuperuserId;
    private Integer createdBySupervisorId;
}
