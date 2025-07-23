package org.example.backend.DTO.User.Retrieval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.backend.Repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder @Data @AllArgsConstructor @NoArgsConstructor
public class UserRetrievalDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;
    private LocalDate hireDate;
    private Integer createdBy;
    private String technicianStatus;
    private List<Integer> interventionsAssigned;
    private List<Integer> interventionsCreated;
    private List<Integer> reportsConcerned;
    private List<Integer> exportationsConcerned;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
