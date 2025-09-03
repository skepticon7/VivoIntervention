//package org.example.backend.Service;
//
//import org.example.backend.DTO.Report.ReportInsertionDTO;
//import org.example.backend.DTO.Report.ReportRetrievalDTO;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public interface ReportService {
//
//    ReportRetrievalDTO createReport(ReportInsertionDTO reportInsertionDTO);
//
//    Page<ReportRetrievalDTO> getAllReports(List<Integer> siteIds, List<Integer> interventionTypeIds, List<Integer> userIds, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
//
//    ReportRetrievalDTO getReportById(Integer id);
//
//    ReportRetrievalDTO updateReport(Integer id, ReportInsertionDTO reportInsertionDTO);
//
//    ReportRetrievalDTO deleteReport(Integer id);
//}
