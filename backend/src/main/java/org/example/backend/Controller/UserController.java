package org.example.backend.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.SupervisorRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.TechnicianRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.UserRetrievalDTO;
import org.example.backend.Entities.Supervisor;
import org.example.backend.Service.UserService;
import org.example.backend.Utils.OnCreate;
import org.example.backend.Utils.OnUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/supervisor/{id}")
    public ResponseEntity<SupervisorRetrievalDTO> getSupervisorById(@PathVariable("id") Integer id){
        SupervisorRetrievalDTO user = userService.getSupervisorById(id);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }

    @GetMapping("/technician/{id}")
    public ResponseEntity<TechnicianRetrievalDTO> getTechnicianById(@PathVariable("id") Integer id){
        TechnicianRetrievalDTO user = userService.getTechnicianById(id);
        return new ResponseEntity<>(user , HttpStatus.OK);
    }


    @DeleteMapping("/deleteTechnician/{id}")
    public ResponseEntity<TechnicianRetrievalDTO> deleteTechnician(@PathVariable("id") Integer id) {
        TechnicianRetrievalDTO technicianRetrievalDTO = userService.deleteTechnician(id);
        return new ResponseEntity<>(technicianRetrievalDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSupervisor/{id}")
    public ResponseEntity<SupervisorRetrievalDTO> deleteSupervisor(@PathVariable("id") Integer id) {
        SupervisorRetrievalDTO supervisorRetrievalDTO = userService.deleteSupervisor(id);
        return new ResponseEntity<>(supervisorRetrievalDTO, HttpStatus.OK);
    }

    @PatchMapping("/updateSupervisor/{id}")
    public ResponseEntity<SupervisorRetrievalDTO> updateSupervisor(@PathVariable("id") Integer id, @Validated(OnUpdate.class) @RequestBody UserInsertionDTO userInsertionDTO) {
        SupervisorRetrievalDTO supervisorRetrievalDTO = userService.updateSupervisor(id, userInsertionDTO);
        return new ResponseEntity<>(supervisorRetrievalDTO, HttpStatus.OK);
    }

    @PatchMapping("/updateTechnician/{id}")
    public ResponseEntity<TechnicianRetrievalDTO> updateTechnician(@PathVariable("id") Integer id , @Validated(OnUpdate.class) @RequestBody TechnicianInsertionDTO technicianInsertionDTO) {
        TechnicianRetrievalDTO technicianRetrievalDTO = userService.updateTechnician(id, technicianInsertionDTO);
        return new ResponseEntity<>(technicianRetrievalDTO, HttpStatus.OK);
    }

}
