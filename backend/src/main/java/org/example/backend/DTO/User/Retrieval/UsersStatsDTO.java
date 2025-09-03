package org.example.backend.DTO.User.Retrieval;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class UsersStatsDTO {
    private Integer total;
    private Integer active;
    private Integer technicians;
    private Integer supervisors;
    private Integer admins;
}
