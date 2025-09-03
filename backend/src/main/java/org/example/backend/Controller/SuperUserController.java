package org.example.backend.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.example.backend.DTO.Intervention.InterventionRetrievalDTO;
import org.example.backend.DTO.SuperUser.SuperUserInsertionDTO;
import org.example.backend.DTO.SuperUser.SuperUserRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.SupervisorSuperuserStatsDTO;
import org.example.backend.Repository.InterventionRepository;
import org.example.backend.Service.SuperUserService;
import org.example.backend.Utils.OnCreate;
import org.example.backend.Utils.OnUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/superuser")
@AllArgsConstructor
public class SuperUserController {

    private final SuperUserService superUserService;

    @PreAuthorize("hasAuthority('SCOPE_ROLE_SUPERUSER')")
    @GetMapping("/getSuperuser/{id}")
    public ResponseEntity<SuperUserRetrievalDTO> getSuperUserById(@PathVariable("id") Integer id){
        SuperUserRetrievalDTO superUserRetrievalDTO = superUserService.getSuperUserById(id);
        return new ResponseEntity<>(superUserRetrievalDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_SUPERUSER')")
    @PatchMapping("/updateSuperuser/{id}")
    public ResponseEntity<SuperUserRetrievalDTO> updateSuperUserById(@PathVariable("id") Integer id , @Validated(OnUpdate.class) @RequestBody SuperUserInsertionDTO superUserInsertionDTO) {
        SuperUserRetrievalDTO superUser = superUserService.updateSuperUser(id , superUserInsertionDTO);
        return new ResponseEntity<>(superUser , HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SCOPE_ROLE_SUPERUSER')")
    @GetMapping("/getSuperuserStats/{id}")
    public ResponseEntity<SupervisorSuperuserStatsDTO> getSuperuserStats(@PathVariable("id") Integer id){
        return new ResponseEntity<>(superUserService.getSuperuserStats(id) , HttpStatus.OK);
    }



}
