package org.example.backend.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTO.Intervention.InterventionInsertionDTO;
import org.example.backend.DTO.Intervention.InterventionRetrievalDTO;
import org.example.backend.Repository.InterventionRepository;
import org.example.backend.Service.InterventionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/intervention")
@AllArgsConstructor
public class InterventionController {

    private final InterventionService interventionService;

    @GetMapping("/getInterventions")
    public ResponseEntity<Page<InterventionRetrievalDTO>> getInterventions(
            @RequestParam(name = "siteIds", required = false) List<Integer> siteIds,
            @RequestParam(name = "InterventionTypeIds" , required = false) List<Integer> interventionTypeIds,
            @RequestParam(name = "userIds" , required = false) List<Integer> userIds,
            @RequestParam(name = "statuses" , required = false) List<String> statuses,
            @RequestParam(name = "prorities" , required = false) List<Integer> priorities,
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ){
        Page<InterventionRetrievalDTO> interventions = interventionService.getAllInterventions(
                siteIds , interventionTypeIds , userIds , statuses , priorities ,  startDate ,  endDate , PageRequest.of(page, size)
        );
        return new ResponseEntity<>(interventions , HttpStatus.OK);
    }
//
//    @GetMapping("/intervention/{id}")
//    public ResponseEntity<InterventionRetrievalDTO> getInterventionById(@PathVariable("id") Integer id) {
//        InterventionRetrievalDTO intervention = interventionService.getInterventionById(id);
//        return new ResponseEntity<>(intervention, HttpStatus.OK);
//    }

    @PostMapping("/createIntervention")
    public ResponseEntity<InterventionRetrievalDTO> createIntervention(@RequestBody @Valid InterventionInsertionDTO interventionInsertionDTO){
        InterventionRetrievalDTO createdIntervention = interventionService.createIntervention(interventionInsertionDTO);
        return new ResponseEntity<>(createdIntervention, HttpStatus.CREATED);
    }

    @PatchMapping("/updateIntervention/{id}")
    public ResponseEntity<InterventionRetrievalDTO> updateIntervention(
            @PathVariable("id") Integer id,
            @RequestBody @Valid InterventionInsertionDTO interventionInsertionDTO
    ){
        InterventionRetrievalDTO intervention = interventionService.updateIntervention(id, interventionInsertionDTO);
        return new ResponseEntity<>(intervention, HttpStatus.OK);
    }

    @DeleteMapping("/deleteIntervention/{id}")
    public ResponseEntity<InterventionRetrievalDTO> deleteIntervention(
            @PathVariable("id") Integer id
    ){
        InterventionRetrievalDTO intervention = interventionService.deleteIntervention(id);
        return new ResponseEntity<>(intervention , HttpStatus.OK);
    }

}
