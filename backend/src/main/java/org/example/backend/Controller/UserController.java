package org.example.backend.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTO.User.Insertion.SupervisorInsertionDTO;
import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.SuperUserRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.SupervisorRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.TechnicianRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.UserRetrievalDTO;
import org.example.backend.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private  UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserRetrievalDTO> getUserById(@PathVariable("id") Integer id){
        UserRetrievalDTO userDTO = userService.getUserById(id);
        return new ResponseEntity<>(userDTO , HttpStatus.OK);
    }

    //default use case
    @PostMapping("/createSuperAdmin")
    public ResponseEntity<SuperUserRetrievalDTO> createSuperAdmin(@Valid @RequestBody UserInsertionDTO userInsertionDTO){
        SuperUserRetrievalDTO superUserRetrievalDTO = null;
        return new ResponseEntity<>(superUserRetrievalDTO , HttpStatus.CREATED);
    }

    //only accessible by super admin
    @PostMapping("createSupervisor")
    public ResponseEntity<SupervisorRetrievalDTO> createSupervisor(@Valid @RequestBody SupervisorInsertionDTO supervisorInsertionDTO){
        SupervisorRetrievalDTO supervisorRetrievalDTO = null;
        return new ResponseEntity<>(supervisorRetrievalDTO , HttpStatus.CREATED);
    }


    //only accessible by super admin or supervisor
    @PostMapping("/createTechnician")
    public ResponseEntity<TechnicianRetrievalDTO> createTechnician(@Valid @RequestBody TechnicianInsertionDTO technicianInsertionDTO){
        TechnicianRetrievalDTO technicianRetrievalDTO = null;
        return new ResponseEntity<>(technicianRetrievalDTO , HttpStatus.CREATED);
    }

}
