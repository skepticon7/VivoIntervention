package org.example.backend.Controller;

import jakarta.persistence.PreUpdate;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTO.Intervention.*;
import org.example.backend.Entities.Intervention;
import org.example.backend.Repository.InterventionRepository;
import org.example.backend.Service.InterventionService;
import org.example.backend.Utils.OnCreate;
import org.example.backend.Utils.OnUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ListResourceBundle;

@RestController
@RequestMapping("/api/intervention")
@AllArgsConstructor
public class InterventionController {

    private final InterventionService interventionService;

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @GetMapping("/getInterventions")
    public ResponseEntity<Page<InterventionRetrievalDTO>> getInterventions(
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "types" , required = false) List<String> types,
            @RequestParam(name = "siteIds", required = false) List<Integer> siteIds,
            @RequestParam(name = "interventionTypesIds" , required = false) List<Integer> interventionTypesIds,
            @RequestParam(name = "userIds" , required = false) List<Integer> userIds,
            @RequestParam(name = "statuses" , required = false) List<String> statuses,
            @RequestParam(name = "priorities" , required = false) List<String> priorities,
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ){

        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : null;
        Page<InterventionRetrievalDTO> interventions = interventionService.getAllInterventions(
               code , types , siteIds , interventionTypesIds , userIds , statuses , priorities ,  startDateTime ,  endDateTime , PageRequest.of(page, size)
        );
        return new ResponseEntity<>(interventions , HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_SUPERUSER')")
    @GetMapping("/getInterventionsForExportation")
    public ResponseEntity<List<InterventionRetrievalDTO>> getInterventionsForExportation(
            @RequestParam("fileName") String fileName,
            @RequestParam(name = "types", required = false) List<String> types,
            @RequestParam(name = "siteIds", required = false) List<Integer> siteIds,
            @RequestParam(name = "interventionTypesIds" , required = false) List<Integer> interventionTypesIds,
            @RequestParam(name = "userIds", required = false) List<Integer> userIds,
            @RequestParam(name = "statuses" , required = false) List<String> statuses,
            @RequestParam(name = "priorities" , required = false) List<String> priorities,
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate
    ){
        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : null;
        List<InterventionRetrievalDTO> interventions = interventionService.getInterventionsForExportation(
                fileName,types,siteIds,interventionTypesIds,userIds,statuses,priorities,startDateTime , endDateTime
        );
        return new ResponseEntity<>(interventions , HttpStatus.OK);
    }



    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_TECHNICIAN' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_SUPERUSER')")
    @GetMapping("/getInterventionsByUserId/{id}")
    public ResponseEntity<Page<InterventionRetrievalDTO>> getInterventionsByUserId(
            @PathVariable("id") Integer id,
            @RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "types", required = false) List<String> types ,
            @RequestParam(name = "siteIds", required = false) List<Integer> siteIds,
            @RequestParam(name = "interventionTypesIds" , required = false) List<Integer> interventionTypesIds,
            @RequestParam(name = "statuses" , required = false) List<String> statuses,
            @RequestParam(name = "priorities" , required = false) List<String> priorities,
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size
    ){
        LocalDateTime startDateTime = startDate != null ? startDate.atStartOfDay() : null;
        LocalDateTime endDateTime = endDate != null ? endDate.atTime(LocalTime.MAX) : null;
        Page<InterventionRetrievalDTO> interventions = interventionService.getInterventionsByUserId(
                id , code , types ,siteIds , interventionTypesIds  , statuses , priorities , startDateTime , endDateTime , PageRequest.of(page , size)
        );
        return new ResponseEntity<>(interventions , HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_TECHNICIAN' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_SUPERUSER')")
    @GetMapping("/{id}")
    public ResponseEntity<InterventionDTO> getInterventionById(@PathVariable("id") Integer id) {
        InterventionDTO intervention = interventionService.getInterventionById(id);
        return new ResponseEntity<>(intervention, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_TECHNICIAN' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_SUPERUSER')")
    @PostMapping("/createIntervention")
    public ResponseEntity<InterventionRetrievalDTO> createIntervention(@RequestBody @Validated(OnCreate.class) InterventionInsertionDTO interventionInsertionDTO){
        InterventionRetrievalDTO createdIntervention = interventionService.createIntervention(interventionInsertionDTO);
        return new ResponseEntity<>(createdIntervention, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_TECHNICIAN' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_SUPERUSER')")
    @PatchMapping("/updateIntervention/{id}")
    public ResponseEntity<InterventionRetrievalDTO> updateIntervention(
            @PathVariable("id") Integer id,
            @RequestBody @Validated(OnUpdate.class) InterventionInsertionDTO interventionInsertionDTO
    ){
        InterventionRetrievalDTO intervention = interventionService.updateIntervention(id, interventionInsertionDTO);
        return new ResponseEntity<>(intervention, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_TECHNICIAN' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_SUPERUSER')")
    @DeleteMapping("/deleteIntervention/{id}")
    public ResponseEntity<InterventionRetrievalDTO> deleteIntervention(
            @PathVariable("id") Integer id
    ){
        InterventionRetrievalDTO intervention = interventionService.deleteIntervention(id);
        return new ResponseEntity<>(intervention , HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_TECHNICIAN' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_SUPERUSER')")
    @GetMapping("/getUserLatestInterventions/{id}")
    public ResponseEntity<List<InterventionDashboard>> getUserLatestInterventions(@PathVariable("id") Integer id) {
        List<InterventionDashboard> interventions = interventionService.getUserLatestInterventions(id);
        return new ResponseEntity<>(interventions , HttpStatus.OK);
    }



    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @GetMapping("/getSuperuserLatestInterventions")
    public ResponseEntity<List<InterventionDashboard>> getSuperuserLatestInterventions() {
        List<InterventionDashboard> interventions = interventionService.getSuperuserLatestInterventions();
        return new ResponseEntity<>(interventions , HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_TECHNICIAN' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_SUPERUSER')")
    @GetMapping("/getInterventionsStats")
    public ResponseEntity<InterventionsStats> getInterventionsStats(@RequestParam("role") String role , @RequestParam("id") Integer id) {
        InterventionsStats stats = interventionService.getInterventionsStats(role , id);
        return new ResponseEntity<>(stats , HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_TECHNICIAN' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_SUPERUSER')")
    @GetMapping("/getInterventionsChartData")
    public ResponseEntity<List<InterventionsChartStats>> getInterventionsChartData(
            @RequestParam(name= "id" , required = false) Integer id
    ){
        return new ResponseEntity<>(interventionService.getInterventionsChartStats(id) , HttpStatus.OK);
    }



}
