package org.example.backend.Service;

import org.example.backend.DTO.Exportation.ExportationInsertionDTO;
import org.example.backend.DTO.Exportation.ExportationRetrievalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ExportationService {

    ExportationRetrievalDTO getExportationByFileName(String fileName);

    Page<ExportationRetrievalDTO> getExportationsByUserId(
            String role,
            Integer id,
            Pageable pageable
    );

    ExportationRetrievalDTO getExportationById(Integer id);

    ExportationRetrievalDTO createExportation(ExportationInsertionDTO exportationInsertionDTO);

    ExportationRetrievalDTO updateExportation(Integer id , ExportationInsertionDTO exportationInsertionDTO);

    ExportationRetrievalDTO deleteExportation(Integer id);

}
