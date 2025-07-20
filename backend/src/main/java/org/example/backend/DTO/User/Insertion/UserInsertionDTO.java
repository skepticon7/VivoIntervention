package org.example.backend.DTO.User.Insertion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class UserInsertionDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String technicianStatus;
    private LocalDate hireDate;
    private Integer createdBy;
}
