package org.example.backend.DTO.Exportation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.Utils.OnCreate;
import org.w3c.dom.stylesheets.LinkStyle;

import java.time.LocalDate;
import java.util.List;

@Builder @NoArgsConstructor @AllArgsConstructor @Data
public class ExportationInsertionDTO {
    @NotBlank(message = "file name cannot be empty" , groups = {OnCreate.class})
    private String fileName;
    @NotBlank(message = "file link cannot be empty" , groups = {OnCreate.class})
    private String fileLink;
    @NotNull(message = "created by cannot be null" , groups = {OnCreate.class})
    private Integer createdBy;
    @NotNull(message = "is super user cannot be null" , groups = {OnCreate.class})
    private boolean isSuperUser;
}
