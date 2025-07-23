package org.example.backend.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTO.Exportation.ExportationInsertionDTO;
import org.example.backend.DTO.Exportation.ExportationRetrievalDTO;
import org.example.backend.Service.ExportationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/exportation")
@AllArgsConstructor
public class ExportationController {

    private final ExportationService exportationService;

    //exclusive to only the superuser and supervisor
    @GetMapping("/getExportations")
    public ResponseEntity<Page<ExportationRetrievalDTO>> getExportations(
            @RequestParam(name = "siteIds" , required = false) List<Integer> siteIds,
            @RequestParam(name = "userIds" , required = false) List<Integer> userIds,
            @RequestParam(name = "startDate" , required = false)LocalDate startDate,
            @RequestParam(name = "endDate" , required = false) LocalDate endDate,
            @RequestParam(name = "supervisorIds" , required = false) List<Integer> supervisorIds,
            @RequestParam(name = "superUserId" , required = false) List<Integer> superuserIds,
            @RequestParam(name = "page" , defaultValue = "0") int page,
            @RequestParam(name = "size" , defaultValue = "5") int size
            ) {
        Page<ExportationRetrievalDTO> exportations = exportationService.getAllExportations(
                siteIds, userIds, startDate, endDate, supervisorIds, superuserIds, PageRequest.of(page, size));
        return new ResponseEntity<>(exportations , HttpStatus.OK);
    }

//    @GetMapping("/exportation/{id}")
//    public ResponseEntity<ExportationRetrievalDTO> getExportationById(@PathVariable("id") Integer id){
//        ExportationRetrievalDTO exportation = exportationService.getExportationById(id);
//        return new ResponseEntity<>(exportation , HttpStatus.OK);
//    }

    @PostMapping("/createExportation")
    public ResponseEntity<ExportationRetrievalDTO> createExportation(@RequestBody @Valid ExportationInsertionDTO exportationInsertionDTO) {
        ExportationRetrievalDTO exportation = exportationService.createExportation(exportationInsertionDTO);
        return new ResponseEntity<>(exportation , HttpStatus.CREATED);
    }

    @PatchMapping("/updateExportation/{id}")
    public ResponseEntity<ExportationRetrievalDTO> updateExportation(@PathVariable("id") Integer id , @RequestBody @Valid ExportationInsertionDTO exportationInsertionDTO){
        ExportationRetrievalDTO exportation = exportationService.updateExportation(id , exportationInsertionDTO);
        return new ResponseEntity<>(exportation , HttpStatus.OK);
    }

    @DeleteMapping("/deleteExportation/{id}")
    public ResponseEntity<ExportationRetrievalDTO> deleteExportation(@PathVariable("id") Integer id){
        ExportationRetrievalDTO exportation = exportationService.deleteExportation(id);
        return new ResponseEntity<>(exportation , HttpStatus.OK);
    }


}
