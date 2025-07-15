package org.example.backend.ServiceImplementation;

import lombok.AllArgsConstructor;
import org.example.backend.DTO.Intervention.InterventionInsertionDTO;
import org.example.backend.Service.InterventionService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InterventionServiceImplementation implements InterventionService {
    @Override
    public InterventionInsertionDTO createIntervention(InterventionInsertionDTO interventionInsertionDTO) {
        return null;
    }

    @Override
    public InterventionInsertionDTO getInterventionById(Integer id) {
        return null;
    }
}
