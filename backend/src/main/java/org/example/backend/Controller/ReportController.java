package org.example.backend.Controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.Report.ReportInsertionDTO;
import org.example.backend.DTO.Report.ReportRetrievalDTO;
import org.example.backend.Service.ReportService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@Validated
public class ReportController {

    private final ReportService reportService;


    @PostMapping("/CreateReport")
    public ReportRetrievalDTO createReport(@RequestBody ReportInsertionDTO dto) {
        return reportService.createReport(dto);
    }


    @GetMapping("/AllReports")
    public List<ReportRetrievalDTO> getAllReports() {
        return reportService.getAllReports();
    }


    @GetMapping("/GetReportbyid")
    public ReportRetrievalDTO getReportById(@PathVariable Integer id) {
        return reportService.getReportById(id);
    }


    @PutMapping("/UpdateReport")
    public ReportRetrievalDTO updateReport(@PathVariable Integer id, @RequestBody ReportInsertionDTO dto) {
        return reportService.updateReport(id, dto);
    }

    @DeleteMapping("DeleteReport")
    public void deleteReport(@PathVariable Integer id) {
        reportService.deleteReport(id);
    }
}
