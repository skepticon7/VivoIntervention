package org.example.backend.DTO.User.Retrieval;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicianRetrievalDTO extends UserRetrievalDTO {

    private List<Integer> supervisorsIds;
}
