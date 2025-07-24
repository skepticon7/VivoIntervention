package org.example.backend.DTO.Report;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.backend.Utils.OnCreate;

import java.util.List;

@Data
public class ReportInsertionDTO {
    @NotBlank(message = "PDF name cannot be blank" , groups = {OnCreate.class})
    private String pdfName;
    @NotBlank(message = "PDF link cannot be blank" , groups = {OnCreate.class})
    private String pdfLink;
    @NotBlank(message = "CreatedBy cannot be blank" , groups = {OnCreate.class})
    private Integer createdBy;
    @NotNull(message = "IsSuperUser cannot be null" , groups = {OnCreate.class})
    private Boolean isSuperuser;
    @NotBlank(message = "Supervisor cannot be blank" , groups = {OnCreate.class})
    private List<Integer> interventionsConcerned;
    @NotBlank(message = "Sites cannot be blank" , groups = {OnCreate.class})
    private List<Integer> sitesConcerned;
    @NotBlank(message = "Supervisors/Technicians cannot be blank" , groups = {OnCreate.class})
    private List<Integer> supervisorsTechniciansConcerned;
    @NotBlank(message = "Intervention types cannot be blank" , groups = {OnCreate.class})
    private List<Integer> interventionTypesConcerned;
}
