package org.example.backend.DTO.Site;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.Utils.OnCreate;

import java.time.LocalTime;

@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class SiteInsertionDTO {
    @NotBlank(message = "Site name cannot be blank" , groups = {OnCreate.class})
    private String siteName;
    @NotBlank(message = "Site code cannot be blank" , groups = {OnCreate.class})
    private String siteCode;
    @NotBlank(message = "Site status cannot be blank" , groups = {OnCreate.class})
    private String siteStatus;
    @NotBlank(message = "Site address cannot be blank" , groups = {OnCreate.class})
    private String siteAdresse;
    @NotBlank(message = "Site location cannot be blank" , groups = {OnCreate.class})
    private String siteLocation;
    @NotBlank(message = "Email cannot be blank" , groups = {OnCreate.class})
    private String email;
    @NotBlank(message = "Phone number cannot be blank" , groups = {OnCreate.class})
    private String phoneNumber;
    @NotNull(message = "Created by cannot be null" , groups = {OnCreate.class})
    private Integer createdById;
    @NotBlank(message = "Start operating hour cannot be blank" , groups = {OnCreate.class})
    private LocalTime startOperatingHour;
    @NotBlank(message = "End operating hour cannot be blank" , groups = {OnCreate.class})
    private LocalTime endOperatingHour;
}
