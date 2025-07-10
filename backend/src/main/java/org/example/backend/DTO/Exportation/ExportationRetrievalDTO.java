package org.example.backend.DTO.Exportation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ExportationRetrievalDTO {
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String fileName;
    private String fileLink;
    private List<UserInsertionDTO> technicians = new ArrayList<>();
    private List<UserInsertionDTO> interventions = new ArrayList<>();
    private UserInsertionDTO exportationCreatedBy;
}
