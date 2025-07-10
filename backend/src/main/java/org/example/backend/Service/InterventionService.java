package org.example.backend.Service;

import org.example.backend.DTO.Intervention.InterventionInsertionDTO;

public interface InterventionService {
    InterventionInsertionDTO createIntervention(InterventionInsertionDTO interventionInsertionDTO);
    InterventionInsertionDTO getInterventionById(Integer id);
}
