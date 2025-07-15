package org.example.backend.DTO.Site;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Builder @Data @AllArgsConstructor @NoArgsConstructor
public class SiteInsertionDTO {
    private String siteName;
    private String siteCode;
    private String siteStatus;
    private String siteAdresse;
    private String email;
    private String phoneNumber;
    private Integer supervisor;
    private LocalTime startOperatingHour;
    private LocalTime endOperatingHour;
}
