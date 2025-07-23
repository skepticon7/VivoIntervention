package org.example.backend.ServiceImplementation;


import lombok.AllArgsConstructor;
import org.example.backend.DTO.Exportation.ExportationInsertionDTO;
import org.example.backend.DTO.Exportation.ExportationRetrievalDTO;
import org.example.backend.Repository.ExportationRepository;
import org.example.backend.Service.ExportationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class ExportationServiceImplementation implements ExportationService {

    private final ExportationRepository exportationRepository;


    @Override
    public Page<ExportationRetrievalDTO> getAllExportations(List<Integer> siteIds, List<Integer> userId, LocalDate startDate, LocalDate endDate, List<Integer> supervisorIds, List<Integer> superuserIds, Pageable pageable) {
        return null;
    }

    @Override
    public ExportationRetrievalDTO getExportationById(Integer id) {
        return null;
    }

    @Override
    public ExportationRetrievalDTO createExportation(ExportationInsertionDTO exportationInsertionDTO) {
        return null;
    }

    @Override
    public ExportationRetrievalDTO updateExportation(Integer id, ExportationInsertionDTO exportationInsertionDTO) {
        return null;
    }

    @Override
    public ExportationRetrievalDTO deleteExportation(Integer id) {
        return null;
    }
}
