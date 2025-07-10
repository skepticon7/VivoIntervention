package org.example.backend.DTO.User.Retrieval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.backend.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@SuperBuilder @Data @AllArgsConstructor @NoArgsConstructor
public class UserRetrievalDTO {
    private Integer id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
