package org.example.backend.Controller;

import lombok.AllArgsConstructor;
import org.example.backend.Configuration.AuthService;
import org.example.backend.DTO.Auth.LoginDTO;
import org.example.backend.DTO.SuperUser.SuperUserInsertionDTO;
import org.example.backend.DTO.SuperUser.SuperUserRetrievalDTO;
import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.SupervisorRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.TechnicianRetrievalDTO;
import org.example.backend.Service.SuperUserService;
import org.example.backend.Service.UserService;
import org.example.backend.Utils.OnCreate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/auth")
@RestController
@AllArgsConstructor
public class AuthController {

    private final SuperUserService superUserService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('ROLE_SUPERUSER', 'ROLE_SUPERVISOR', 'ROLE_TECHNICIAN')")
    public ResponseEntity<?> authentication(Authentication authentication) {
        if (authentication == null) {
            return new ResponseEntity<>("No user is authenticated", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String , String>> login(@Validated(OnCreate.class) @RequestBody LoginDTO loginDTO) {
        Map<String , String> token = new HashMap<>();
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        token.put("jwtToken" , authService.generateToken(authentication));
        return new ResponseEntity<>(token , HttpStatus.OK);
    }

    @PostMapping("/createSupervisor")
    public ResponseEntity<SupervisorRetrievalDTO> createSupervisor(@Validated(OnCreate.class) @RequestBody UserInsertionDTO userInsertionDTO){
        SupervisorRetrievalDTO supervisorRetrievalDTO = userService.createSupervisor(userInsertionDTO);
        return new ResponseEntity<>(supervisorRetrievalDTO , HttpStatus.CREATED);
    }

    @PostMapping("/createTechnician")
    public ResponseEntity<TechnicianRetrievalDTO> createTechnician(@Validated(OnCreate.class) @RequestBody TechnicianInsertionDTO technicianInsertionDTO){
        TechnicianRetrievalDTO technicianRetrievalDTO = userService.createTechnician(technicianInsertionDTO);
        return new ResponseEntity<>(technicianRetrievalDTO , HttpStatus.CREATED);
    }

    @PostMapping("/createSuperuser")
    public ResponseEntity<SuperUserRetrievalDTO> createSuperUser(@Validated(OnCreate.class) @RequestBody SuperUserInsertionDTO superUserInsertionDTO){
        SuperUserRetrievalDTO superUserRetrievalDTO = superUserService.createSuperUser(superUserInsertionDTO);
        return new ResponseEntity<>(superUserRetrievalDTO , HttpStatus.CREATED);
    }



}
