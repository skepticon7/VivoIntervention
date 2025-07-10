package org.example.backend.DTO.Site;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SiteRetrievalDTO {
    private Integer id;
    private String siteName;
    private String siteCode;
    private String siteStatus;
    private String siteAdresse;
    private String email;
    private String phoneNumber;
    private String supervisor;
    private Integer interventionsMade;
    private Integer techniciansAssigned;
    private LocalTime startOperatingHour;
    private LocalTime endOperatingHour;
}
