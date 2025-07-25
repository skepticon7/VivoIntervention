package org.example.backend.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.Report.ReportInsertionDTO;
import org.example.backend.DTO.Report.ReportRetrievalDTO;
import org.example.backend.Service.ReportService;
import org.example.backend.Utils.OnCreate;
import org.example.backend.Utils.OnUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/report")
@AllArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_SUPER_USER' , 'ROLE_SUPERVISOR')")
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/createReport")
    public ResponseEntity<ReportRetrievalDTO> createReport(@RequestBody @Validated(OnCreate.class) ReportInsertionDTO reportInsertionDTO) {
        ReportRetrievalDTO report = reportService.createReport(reportInsertionDTO);
        return new ResponseEntity<>(report, HttpStatus.CREATED);
    }

    @GetMapping("/allReports")
    public ResponseEntity<Page<ReportRetrievalDTO>> getAllReports(
            @RequestParam(value = "siteIds", required = false) List<Integer> siteIds,
            @RequestParam(value = "interventionTypeIds", required = false) List<Integer> interventionTypeIds,
            @RequestParam(value = "userIds", required = false) List<Integer> userIds,
            @RequestParam(value = "startDate", required = false) LocalDateTime startDate,
            @RequestParam(value = "endDate", required = false) LocalDateTime endDate,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        Page<ReportRetrievalDTO> reports = reportService.getAllReports(siteIds , interventionTypeIds, userIds, startDate, endDate, PageRequest.of(page, size));
        return new ResponseEntity<>(reports , HttpStatus.OK);
    }

    @GetMapping("/getReportById/{id}")
    public ResponseEntity<ReportRetrievalDTO> getReportById(@PathVariable("id") Integer id) {
        ReportRetrievalDTO report = reportService.getReportById(id);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @PatchMapping("/updateReportById/{id}")
    public ResponseEntity<ReportRetrievalDTO> updateReport(@PathVariable("id") Integer id, @RequestBody @Validated(OnUpdate.class) ReportInsertionDTO reportInsertionDTO) {
         ReportRetrievalDTO report = reportService.updateReport(id, reportInsertionDTO);
         return new ResponseEntity<>(report , HttpStatus.OK);
    }

    @DeleteMapping("/deleteReport/{id}")
    public ResponseEntity<ReportRetrievalDTO> deleteReport(@PathVariable("id") Integer id) {
        ReportRetrievalDTO reportDeleted = reportService.deleteReport(id);
        return new ResponseEntity<>(reportDeleted , HttpStatus.OK);
    }
}
