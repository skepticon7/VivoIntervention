package org.example.backend.Service;

import org.example.backend.DTO.Intervention.*;
import org.example.backend.Entities.Intervention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface InterventionService {
    InterventionRetrievalDTO createIntervention(InterventionInsertionDTO interventionInsertionDTO);
    InterventionDTO getInterventionById(Integer id);
    Page<InterventionRetrievalDTO> getAllInterventions(String code , List<String> types , List<Integer> siteIds , List<Integer> interventionTypeIds , List<Integer> userIds , List<String> statuses , List<String> priorities , LocalDateTime startDate , LocalDateTime endDate , Pageable pageable);
    List<InterventionRetrievalDTO> getInterventionsForExportation(String fileName , List<String> types , List<Integer> siteIds , List<Integer> interventionTypeIds , List<Integer> userIds , List<String> statuses , List<String> priorities , LocalDateTime startDate , LocalDateTime endDate);
    InterventionRetrievalDTO updateIntervention(Integer id , InterventionInsertionDTO interventionInsertionDTO);
    InterventionRetrievalDTO deleteIntervention(Integer id);
    List<InterventionDashboard> getUserLatestInterventions(Integer id);
    List<InterventionDashboard> getSuperuserLatestInterventions();
    Page<InterventionRetrievalDTO> getInterventionsByUserId(Integer technicianId , String code , List<String> types ,  List<Integer> siteIds, List<Integer> interventionTypeIds, List<String> statuses, List<String> priorities, LocalDateTime startDate, LocalDateTime endDate , Pageable pageable);
    InterventionsStats getInterventionsStats(String role , Integer id);
    List<InterventionsChartStats> getInterventionsChartStats(Integer id );
}
