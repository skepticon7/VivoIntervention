package org.example.backend.DTO.SuperUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class SuperUserRetrievalDTO {
    private Integer Id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String role;
    private String technicianStatus;
    private LocalDate hireDate;
    private List<Integer> sites;
    private List<Integer> users;
    private List<Integer> interventions;
    private List<Integer> reports;
    private List<Integer> exportations;
    private List<Integer> interventionTypes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
