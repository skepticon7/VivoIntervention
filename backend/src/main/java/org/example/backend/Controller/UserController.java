package org.example.backend.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTO.UserInsertionDTO;
import org.example.backend.DTO.UserRetrievalDTO;
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


    @PostMapping("/createUser")
    public ResponseEntity<UserRetrievalDTO> createNewUser(@Valid @RequestBody UserInsertionDTO userInsertionDTO){
        UserRetrievalDTO userRetrievalDTO =  userService.createUser( userInsertionDTO);
        return new ResponseEntity<>(userRetrievalDTO , HttpStatus.CREATED);
    }

}
