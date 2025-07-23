package org.example.backend.Controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.InterventionType.InterventionTypeInsertionDTO;
import org.example.backend.DTO.InterventionType.InterventionTypeRetrievalDTO;
import org.example.backend.Service.InterventionTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interventionType")
@RequiredArgsConstructor
public class InterventionTypeController {

    private final InterventionTypeService interventionTypeService;


    @PostMapping("/createIntervention")
    public InterventionTypeRetrievalDTO createInterventionType(@RequestBody InterventionTypeInsertionDTO dto) {
        return interventionTypeService.createInterventionType(dto);
    }


    @GetMapping("/allInterventionTypes")
    public List<InterventionTypeRetrievalDTO> getAllInterventionTypes() {
        return interventionTypeService.getAllInterventionTypes();
    }


    @GetMapping("/interventionType/{id}")
    public InterventionTypeRetrievalDTO getInterventionTypeById(@PathVariable Integer id) {
        return interventionTypeService.getInterventionTypeById(id);
    }


    @PutMapping("/updateIntervention/{id}")
    public InterventionTypeRetrievalDTO updateInterventionType(@PathVariable Integer id,
                                                               @RequestBody InterventionTypeInsertionDTO dto) {
        return interventionTypeService.updateInterventionType(id, dto);
    }


    @DeleteMapping("/deleteInterventionType/{id}")
    public void deleteInterventionType(@PathVariable Integer id) {
        interventionTypeService.deleteInterventionType(id);
    }
}
