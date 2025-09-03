package org.example.backend.Mapper.Site;

import org.example.backend.DTO.Site.SiteInsertionDTO;
import org.example.backend.DTO.Site.SiteRetrievalDTO;
import org.example.backend.Entities.*;
import org.example.backend.Enums.SiteStatus;
import org.example.backend.Mapper.User.SupervisorDtoMapper;

import java.util.ArrayList;

public class SiteDtoMapper {
    public static Site toEntity(SiteInsertionDTO siteInsertionDTO){
        if(siteInsertionDTO == null) return null;
        return Site.builder()
                .siteAdresse(siteInsertionDTO.getSiteAdresse())
                .siteName(siteInsertionDTO.getSiteName())
                .siteLocation(siteInsertionDTO.getSiteLocation())
                .email(siteInsertionDTO.getEmail())
                .phoneNumber(siteInsertionDTO.getPhoneNumber())
                .interventions(new ArrayList<>())
                .siteCode(siteInsertionDTO.getSiteCode())
                .siteStatus(SiteStatus.valueOf(siteInsertionDTO.getSiteStatus()))
                .startOperatingHour(siteInsertionDTO.getStartOperatingHour())
                .endOperatingHour(siteInsertionDTO.getEndOperatingHour())
                .build();
    }


    public static SiteRetrievalDTO toDto(Site site) {
        if(site == null) return null;
        return SiteRetrievalDTO.builder()
                .id(site.getId())
                .siteAdresse(site.getSiteAdresse())
                .siteName(site.getSiteName())
                .siteCode(site.getSiteCode())
                .siteStatus(site.getSiteStatus().name())
                .siteLocation(site.getSiteLocation())
                .startOperatingHour(site.getStartOperatingHour())
                .endOperatingHour(site.getEndOperatingHour())
                .email(site.getEmail())
                .phoneNumber(site.getPhoneNumber())
                .createdAt(site.getCreatedAt())
                .updatedAt(site.getUpdatedAt())
                .interventionsMade(site.getInterventions().stream().map(Intervention::getId).toList())
                .build();
    }

}
