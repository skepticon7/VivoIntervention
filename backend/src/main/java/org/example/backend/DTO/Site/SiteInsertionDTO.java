package org.example.backend.DTO.Site;

import java.time.LocalTime;

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
