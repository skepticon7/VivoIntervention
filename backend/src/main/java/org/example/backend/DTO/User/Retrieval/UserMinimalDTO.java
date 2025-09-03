package org.example.backend.DTO.User.Retrieval;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserMinimalDTO {
    private Integer id;
    private String fullName;
}
