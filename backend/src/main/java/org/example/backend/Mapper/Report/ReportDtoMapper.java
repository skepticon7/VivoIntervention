package org.example.backend.Mapper.Report;

import org.example.backend.DTO.Report.ReportInsertionDTO;
import org.example.backend.DTO.Report.ReportRetrievalDTO;
import org.example.backend.Entities.*;

public class ReportDtoMapper {
    public static ReportRetrievalDTO toDto(Report report) {
        if(report == null) return null;
        return ReportRetrievalDTO.builder()
                .id(report.getId())
                .pdfLink(report.getPdfLink())
                .pdfName(report.getPdfName())
                .interventions(report.getInterventions().stream().map(Intervention::getId).toList())
                .sites(report.getSites().stream().map(Site::getId).toList())
                .interventionTypes(report.getInterventionTypes().stream().map(InterventionType::getId).toList())
                .supervisorsTechnicians(report.getSupervisors_technicians().stream().map(User::getId).toList())
                .createdAt(report.getCreatedAt())
                .updatedAt(report.getUpdatedAt())
                .createdById(report.getCreatedBySuperuser() == null ? report.getCreatedBySupervisor().getId() : report.getCreatedBySuperuser().getId())
                .createdByRole(report.getCreatedBySuperuser() == null ? report.getCreatedBySupervisor().getRole() : "SUPER_USER")
                .build();
    }

    public static Report toEntity(ReportInsertionDTO reportInsertionDTO){
        if (reportInsertionDTO == null) {
            return null;
        }
        return Report.builder()
                .pdfLink(reportInsertionDTO.getPdfLink())
                .pdfName(reportInsertionDTO.getPdfName())
                .build();
    }

}
