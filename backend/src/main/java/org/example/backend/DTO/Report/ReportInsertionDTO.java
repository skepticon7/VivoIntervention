//package org.example.backend.DTO.Report;
//
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import lombok.Data;
//import org.example.backend.Utils.OnCreate;
//
//import java.util.List;
//
//@Data
//public class ReportInsertionDTO {
//    @NotBlank(message = "PDF name cannot be blank" , groups = {OnCreate.class})
//    private String pdfName;
//    @NotBlank(message = "PDF link cannot be blank" , groups = {OnCreate.class})
//    private String pdfLink;
//    @NotNull(message = "CreatedBy cannot be blank" , groups = {OnCreate.class})
//    private Boolean isSuperuser;
//    @NotEmpty(message = "Supervisor cannot be blank" , groups = {OnCreate.class})
//    private List<Integer> interventionsConcerned;
//    @NotEmpty(message = "Sites cannot be blank" , groups = {OnCreate.class})
//    private List<Integer> sitesConcerned;
//    @NotEmpty(message = "Supervisors/Technicians cannot be blank" , groups = {OnCreate.class})
//    private List<Integer> supervisorsTechniciansConcerned;
//    @NotEmpty(message = "Intervention types cannot be blank" , groups = {OnCreate.class})
//    private List<Integer> interventionTypesConcerned;
//}
