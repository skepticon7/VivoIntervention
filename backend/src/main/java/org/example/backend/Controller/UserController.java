package org.example.backend.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTO.User.Insertion.SuperUserInsertionDTO;
import org.example.backend.DTO.User.Insertion.SupervisorInsertionDTO;
import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.SuperUserRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.SupervisorRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.TechnicianRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.UserRetrievalDTO;
import org.example.backend.Entities.Supervisor;
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
    public ResponseEntity<SuperUserRetrievalDTO> createSuperAdmin(@Valid @RequestBody SuperUserInsertionDTO superUserInsertionDTO){
        SuperUserRetrievalDTO superUserRetrievalDTO = userService.createSuperUser(superUserInsertionDTO);
        return new ResponseEntity<>(superUserRetrievalDTO , HttpStatus.CREATED);
    }





}
