package org.example.backend.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTO.Exportation.ExportationInsertionDTO;
import org.example.backend.DTO.Exportation.ExportationRetrievalDTO;
import org.example.backend.Service.ExportationService;
import org.example.backend.Utils.OnCreate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/exportation")
@AllArgsConstructor
public class ExportationController {

    private final ExportationService exportationService;

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_SUPERUSER')")
    @GetMapping("/getExportationsByUser")
    public ResponseEntity<Page<ExportationRetrievalDTO>> getExportations(
            @RequestParam("role") String role,
            @RequestParam("id") Integer id,
            @RequestParam(name = "page" , defaultValue = "0") int page,
            @RequestParam(name = "size" , defaultValue = "5") int size
            ) {
        Page<ExportationRetrievalDTO> exportations = exportationService.getExportationsByUserId(role , id , PageRequest.of(page , size));
        return new ResponseEntity<>(exportations , HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_SUPERUSER')")
    @GetMapping("/exportation/{id}")
    public ResponseEntity<ExportationRetrievalDTO> getExportationById(@PathVariable("id") Integer id){
        ExportationRetrievalDTO exportation = exportationService.getExportationById(id);
        return new ResponseEntity<>(exportation , HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_SUPERUSER')")
    @PostMapping("/createExportation")
    public ResponseEntity<ExportationRetrievalDTO> createExportation(@RequestBody @Validated(OnCreate.class) ExportationInsertionDTO exportationInsertionDTO) {
        ExportationRetrievalDTO exportation = exportationService.createExportation(exportationInsertionDTO);
        return new ResponseEntity<>(exportation , HttpStatus.CREATED);
    }


}
