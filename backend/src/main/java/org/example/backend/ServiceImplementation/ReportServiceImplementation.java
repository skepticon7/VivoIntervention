package org.example.backend.ServiceImplementation;
import org.example.backend.Service.ReportService;


import org.example.backend.DTO.Report.ReportInsertionDTO;
import org.example.backend.DTO.Report.ReportRetrievalDTO;
import org.example.backend.Entities.Report;
import org.example.backend.Entities.SuperUser;
import org.example.backend.Entities.Supervisor;
import org.example.backend.Repository.ReportRepository;
import org.example.backend.Repository.SuperUserRepository;
import org.example.backend.Repository.SupervisorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImplementation implements ReportService {

    private final ReportRepository reportRepository;
    private final SuperUserRepository superUserRepository;
    private final SupervisorRepository supervisorRepository;

    public ReportServiceImplementation(ReportRepository reportRepository,
                                       SuperUserRepository superUserRepository,
                                       SupervisorRepository supervisorRepository) {
        this.reportRepository = reportRepository;
        this.superUserRepository = superUserRepository;
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public ReportRetrievalDTO createReport(ReportInsertionDTO dto) {
        SuperUser superUser = superUserRepository.findById(dto.getCreatedBySuperuserId())
                .orElseThrow(() -> new RuntimeException("SuperUser not found"));
        Supervisor supervisor = supervisorRepository.findById(dto.getCreatedBySupervisorId())
                .orElseThrow(() -> new RuntimeException("Supervisor not found"));

        Report report = Report.builder()
                .pdfName(dto.getPdfName())
                .pdfLink(dto.getPdfLink())
                .createdBySuperuser(superUser)
                .createdBySupervisor(supervisor)
                .build();

        Report saved = reportRepository.save(report);

        return mapToDto(saved);
    }

    @Override
    public List<ReportRetrievalDTO> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream().map(this::mapToDto).toList();
    }

    @Override
    public ReportRetrievalDTO getReportById(Integer id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        return mapToDto(report);
    }

    @Override
    public ReportRetrievalDTO updateReport(Integer id, ReportInsertionDTO dto) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        SuperUser superUser = superUserRepository.findById(dto.getCreatedBySuperuserId())
                .orElseThrow(() -> new RuntimeException("SuperUser not found"));
        Supervisor supervisor = supervisorRepository.findById(dto.getCreatedBySupervisorId())
                .orElseThrow(() -> new RuntimeException("Supervisor not found"));

        report.setPdfName(dto.getPdfName());
        report.setPdfLink(dto.getPdfLink());
        report.setCreatedBySuperuser(superUser);
        report.setCreatedBySupervisor(supervisor);

        Report updated = reportRepository.save(report);
        return mapToDto(updated);
    }

    @Override
    public void deleteReport(Integer id) {
        if (!reportRepository.existsById(id)) {
            throw new RuntimeException("Report not found");
        }
        reportRepository.deleteById(id);
    }

    private ReportRetrievalDTO mapToDto(Report report) {
        ReportRetrievalDTO dto = new ReportRetrievalDTO();
        dto.setId(report.getId());
        dto.setPdfName(report.getPdfName());
        dto.setPdfLink(report.getPdfLink());
        dto.setCreatedAt(report.getCreatedAt());
        dto.setUpdatedAt(report.getUpdatedAt());
        return dto;
    }
}
