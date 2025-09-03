package org.example.backend.Controller;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.InterventionType.InterventionTypeInsertionDTO;
import org.example.backend.DTO.InterventionType.InterventionTypeRetrievalDTO;
import org.example.backend.Service.InterventionTypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interventionType")
@RequiredArgsConstructor
public class InterventionTypeController {

    private final InterventionTypeService interventionTypeService;


    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @PostMapping("/createInterventionType")
    public InterventionTypeRetrievalDTO createInterventionType(@RequestBody InterventionTypeInsertionDTO dto) {
        return interventionTypeService.createInterventionType(dto);
    }


    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_TECHNICIAN')")
    @GetMapping("/allInterventionTypes")
    public List<InterventionTypeRetrievalDTO> getAllInterventionTypes() {
        return interventionTypeService.getAllInterventionTypes();
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_TECHNICIAN')")
    @GetMapping("/interventionType/{id}")
    public InterventionTypeRetrievalDTO getInterventionTypeById(@PathVariable Integer id) {
        return interventionTypeService.getInterventionTypeById(id);
    }


    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @PatchMapping("/updateIntervention/{id}")
    public InterventionTypeRetrievalDTO updateInterventionType(@PathVariable Integer id,
                                                               @RequestBody InterventionTypeInsertionDTO dto) {
        return interventionTypeService.updateInterventionType(id, dto);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @DeleteMapping("/deleteInterventionType/{id}")
    public void deleteInterventionType(@PathVariable Integer id) {
        interventionTypeService.deleteInterventionType(id);
    }
}
