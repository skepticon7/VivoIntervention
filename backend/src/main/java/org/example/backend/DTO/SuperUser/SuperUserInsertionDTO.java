package org.example.backend.DTO.SuperUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class SuperUserInsertionDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
}
