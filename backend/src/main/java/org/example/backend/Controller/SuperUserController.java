package org.example.backend.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.example.backend.DTO.SuperUser.SuperUserInsertionDTO;
import org.example.backend.DTO.SuperUser.SuperUserRetrievalDTO;
import org.example.backend.Service.SuperUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/superuser")
@AllArgsConstructor
public class SuperUserController {

    private final SuperUserService superUserService;

    @PostMapping("/createSuperuser")
    public ResponseEntity<SuperUserRetrievalDTO> createSuperUser(@Valid @RequestBody SuperUserInsertionDTO superUserInsertionDTO){
        SuperUserRetrievalDTO superUserRetrievalDTO = superUserService.createSuperUser(superUserInsertionDTO);
        return new ResponseEntity<>(superUserRetrievalDTO , HttpStatus.CREATED);
    }

    @GetMapping("/getSuperuser/{id}")
    public ResponseEntity<SuperUserRetrievalDTO> getSuperUserById(@PathVariable("id") Integer id){
        SuperUserRetrievalDTO superUserRetrievalDTO = superUserService.getSuperUserById(id);
        return new ResponseEntity<>(superUserRetrievalDTO, HttpStatus.OK);
    }




}
