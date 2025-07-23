package org.example.backend.Service;

import org.example.backend.DTO.InterventionType.InterventionTypeInsertionDTO;
import org.example.backend.DTO.InterventionType.InterventionTypeRetrievalDTO;

import java.util.List;

public interface InterventionTypeService {

    InterventionTypeRetrievalDTO createInterventionType(InterventionTypeInsertionDTO dto);

    List<InterventionTypeRetrievalDTO> getAllInterventionTypes();

    InterventionTypeRetrievalDTO getInterventionTypeById(Integer id);

    InterventionTypeRetrievalDTO updateInterventionType(Integer id, InterventionTypeInsertionDTO dto);

    InterventionTypeRetrievalDTO deleteInterventionType(Integer id);
}
