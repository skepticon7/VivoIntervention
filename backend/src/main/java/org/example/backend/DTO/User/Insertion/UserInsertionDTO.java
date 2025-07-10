package org.example.backend.DTO.User.Insertion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data @AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class UserInsertionDTO {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String password;
}
