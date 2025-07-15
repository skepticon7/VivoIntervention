package org.example.backend.Mapper.Site;

import org.example.backend.DTO.Site.SiteInsertionDTO;
import org.example.backend.DTO.Site.SiteRetrievalDTO;
import org.example.backend.Entities.Site;
import org.example.backend.Enums.SiteStatus;
import org.example.backend.Mapper.User.SupervisorDtoMapper;

public class SiteDtoMapper {
    public static Site toEntity(SiteInsertionDTO siteInsertionDTO){
        if(siteInsertionDTO == null) return null;
        return Site.builder()
                .siteAdresse(siteInsertionDTO.getSiteAdresse())
                .siteName(siteInsertionDTO.getSiteName())
                .siteCode(siteInsertionDTO.getSiteCode())
                .siteStatus(SiteStatus.valueOf(siteInsertionDTO.getSiteStatus()))
                .startOperatingHour(siteInsertionDTO.getStartOperatingHour())
                .endOperatingHour(siteInsertionDTO.getEndOperatingHour())
                .build();
    }


    public static SiteRetrievalDTO toDto(Site site) {
        if(site == null) return null;
        return SiteRetrievalDTO.builder()
                .siteAdresse(site.getSiteAdresse())
                .siteName(site.getSiteName())
                .siteCode(site.getSiteCode())
                .siteStatus(site.getSiteStatus().name())
                .startOperatingHour(site.getStartOperatingHour())
                .endOperatingHour(site.getEndOperatingHour())
                .email(site.getEmail())
                .phoneNumber(site.getPhoneNumber())
                .supervisor(site.getSupervisor().getFullName())
                .interventionsMade(site.getInterventions().size())
                .techniciansAssigned(site.getTechnicians().size())
                .id(site.getId())
                .build();
    }

}
