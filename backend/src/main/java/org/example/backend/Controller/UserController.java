package org.example.backend.Controller;

import lombok.AllArgsConstructor;
import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.*;
import org.example.backend.Enums.TechnicianStatus;
import org.example.backend.Service.UserService;
import org.example.backend.Utils.OnUpdate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {


    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @GetMapping("/supervisor/{id}")
    public ResponseEntity<SupervisorRetrievalDTO> getSupervisorById(@PathVariable("id") Integer id){
        SupervisorRetrievalDTO user = userService.getSupervisorById(id);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_TECHNICIAN')")
    @GetMapping("/technician/{id}")
    public ResponseEntity<TechnicianRetrievalDTO> getTechnicianById(@PathVariable("id") Integer id){
        TechnicianRetrievalDTO user = userService.getTechnicianById(id);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @GetMapping("/getAllTechnicians")
    public ResponseEntity<List<TechnicianRetrievalDTO>> getAllTechnicians(
            @RequestParam(name = "page", required = false , defaultValue = "0") int page ,
            @RequestParam(name = "size" , required = false , defaultValue = "5") int size) {
        List<TechnicianRetrievalDTO> technicians = userService.getAllTechincians(page , size);
        return new ResponseEntity<>(technicians , HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @GetMapping("/getUsersForIntervention")
    public ResponseEntity<List<UserRetrievalDTO>> getUsersForIntervention(
           ) {
        List<UserRetrievalDTO> users = userService.getUsersForIntervention();
        return new ResponseEntity<>(users , HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @DeleteMapping("/deleteTechnician/{id}")
    public ResponseEntity<TechnicianRetrievalDTO> deleteTechnician(@PathVariable("id") Integer id) {
        TechnicianRetrievalDTO technicianRetrievalDTO = userService.deleteTechnician(id);
        return new ResponseEntity<>(technicianRetrievalDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @DeleteMapping("/deleteSupervisor/{id}")
    public ResponseEntity<SupervisorRetrievalDTO> deleteSupervisor(@PathVariable("id") Integer id) {
        SupervisorRetrievalDTO supervisorRetrievalDTO = userService.deleteSupervisor(id);
        return new ResponseEntity<>(supervisorRetrievalDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @PatchMapping("/updateSupervisor/{id}")
    public ResponseEntity<SupervisorRetrievalDTO> updateSupervisor(@PathVariable("id") Integer id, @Validated(OnUpdate.class) @RequestBody UserInsertionDTO userInsertionDTO) {
        SupervisorRetrievalDTO supervisorRetrievalDTO = userService.updateSupervisor(id, userInsertionDTO);
        return new ResponseEntity<>(supervisorRetrievalDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_TECHNICIAN')")
    @PatchMapping("/updateTechnician/{id}")
    public ResponseEntity<TechnicianRetrievalDTO> updateTechnician(@PathVariable("id") Integer id , @Validated(OnUpdate.class) @RequestBody TechnicianInsertionDTO technicianInsertionDTO) {
        TechnicianRetrievalDTO technicianRetrievalDTO = userService.updateTechnician(id, technicianInsertionDTO);
        return new ResponseEntity<>(technicianRetrievalDTO, HttpStatus.OK);
    }


    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_TECHNICIAN')")
    @GetMapping("/getTechnicianStats/{id}")
    public ResponseEntity<TechnicianStatsDTO> getTechnicianStats(@PathVariable("id") Integer id){
        return new ResponseEntity<>(userService.getTechnicianStats(id) , HttpStatus.OK);
    }

    @GetMapping("/getSupervisorStats/{id}")
    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    public ResponseEntity<SupervisorSuperuserStatsDTO> getSupervisorStats(@PathVariable("id") Integer id){
        return new ResponseEntity<>(userService.getSupervisorStats(id) , HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @GetMapping("/getUsersStats")
    public ResponseEntity<UsersStatsDTO> getUsersStats() {
        UsersStatsDTO stats = userService.getUsersStats();
        return new ResponseEntity<>(stats , HttpStatus.OK);
    }

    @GetMapping("/getTechniciansSupervisors")
    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    public ResponseEntity<Page<UserRetrievalDTO>> getTechnicianSupervisors(
            @RequestParam(name = "name" , required = false ) String name,
            @RequestParam(name = "statuses" , required = false) List<TechnicianStatus> statuses,
            @RequestParam(name = "roles" , required = false) List<String> roles,
            @RequestParam(name = "page" , required = false , defaultValue = "0") int page,
            @RequestParam(name = "size" , required = false , defaultValue = "5") int size
    ){
        Page<UserRetrievalDTO> users = userService.findTechniciansSupervisors(name , statuses , roles , PageRequest.of(page , size));
        return new ResponseEntity<>(users , HttpStatus.OK);
    }


}
