package org.example.backend.DTO.Site;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SiteRetrievalDTO {
    private Integer id;
    private String siteName;
    private String siteCode;
    private String siteStatus;
    private String siteLocation;
    private String siteAdresse;
    private String email;
    private String phoneNumber;
    private List<Integer> interventionsMade;
    private List<Integer> reportsConceted;
    private List<Integer> exportationsConcerned;
    private LocalTime startOperatingHour;
    private LocalTime endOperatingHour;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
