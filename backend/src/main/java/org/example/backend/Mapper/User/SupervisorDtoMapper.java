package org.example.backend.Mapper.User;

import org.example.backend.DTO.Site.SiteRetrievalDTO;
import org.example.backend.DTO.User.Insertion.SupervisorInsertionDTO;
import org.example.backend.DTO.User.Retrieval.SupervisorRetrievalDTO;
import org.example.backend.Entities.Supervisor;
import org.example.backend.Enums.Specialization;
import org.example.backend.Enums.TechnicianStatus;
import org.example.backend.Mapper.Site.SiteDtoMapper;

public class SupervisorDtoMapper {
    public static Supervisor toEntity(SupervisorInsertionDTO supervisorInsertionDTO){
        return Supervisor.builder()
                .fullName(supervisorInsertionDTO.getFullName())
                .email(supervisorInsertionDTO.getEmail())
                .phoneNumber(supervisorInsertionDTO.getPhoneNumber())
                .speciality(Specialization.valueOf(supervisorInsertionDTO.getSpeciality()))
                .technicianStatus(TechnicianStatus.valueOf(supervisorInsertionDTO.getTechnicianStatus()))
                .build();
    }

    public static SupervisorRetrievalDTO toDto(Supervisor supervisor) {
        return SupervisorRetrievalDTO.builder()
                .fullName(supervisor.getFullName())
                .email(supervisor.getEmail())
                .phoneNumber(supervisor.getPhoneNumber())
                .speciality(supervisor.getSpeciality().name())
                .technicianStatus(supervisor.getTechnicianStatus().name())
                .teamSize(supervisor.getTeam().size())
                .site(SiteDtoMapper.toDto(supervisor.getMainSite()))
                .build();
    }
}
