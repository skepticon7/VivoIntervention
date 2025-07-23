package org.example.backend.Service;

import org.example.backend.DTO.Intervention.InterventionInsertionDTO;
import org.example.backend.DTO.Intervention.InterventionRetrievalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface InterventionService {
    InterventionRetrievalDTO createIntervention(InterventionInsertionDTO interventionInsertionDTO);
    InterventionRetrievalDTO getInterventionById(Integer id);
    Page<InterventionRetrievalDTO> getAllInterventions(List<Integer> siteIds , List<Integer> interventionTypeIds , List<Integer> userIds , List<String> statuses , List<Integer> priorities , LocalDate startDate , LocalDate endDate , Pageable pageable);
    InterventionRetrievalDTO updateIntervention(Integer id , InterventionInsertionDTO interventionInsertionDTO);
    InterventionRetrievalDTO deleteIntervention(Integer id);
}
