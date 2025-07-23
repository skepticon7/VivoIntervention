package org.example.backend.Service;

import org.example.backend.DTO.Report.ReportInsertionDTO;
import org.example.backend.DTO.Report.ReportRetrievalDTO;

import java.util.List;

public interface ReportService {

    ReportRetrievalDTO createReport(ReportInsertionDTO dto);

    List<ReportRetrievalDTO> getAllReports();

    ReportRetrievalDTO getReportById(Integer id);

    ReportRetrievalDTO updateReport(Integer id, ReportInsertionDTO dto);

    void deleteReport(Integer id);
}
