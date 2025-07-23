package org.example.backend.DTO.Report;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportRetrievalDTO {
    private Integer id;
    private String pdfName;
    private String pdfLink;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
