package org.example.backend.DTO.User.Retrieval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data @SuperBuilder @AllArgsConstructor @NoArgsConstructor
public class SupervisorRetrievalDTO extends UserRetrievalDTO{
    private List<Integer> techniciansCreated;
    private List<Integer> reportsCreated;
    private List<Integer> exportationsCreated;
}
