package org.example.backend.ServiceImplementation;
import lombok.AllArgsConstructor;
import org.example.backend.Configuration.AuthService;
import org.example.backend.Configuration.CustomUserDetails;
import org.example.backend.Entities.*;
import org.example.backend.Exceptions.NotFoundException;
import org.example.backend.Mapper.Report.ReportDtoMapper;
import org.example.backend.Repository.*;
import org.example.backend.Service.ReportService;


import org.example.backend.DTO.Report.ReportInsertionDTO;
import org.example.backend.DTO.Report.ReportRetrievalDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportServiceImplementation implements ReportService {

    private final ReportRepository reportRepository;
    private final SuperUserRepository superUserRepository;
    private final SupervisorRepository supervisorRepository;
    private final AuthService authService;
    private final InterventionTypeRepository interventionTypeRepository;
    private final SiteRepository siteRepository;
    private final UserRepository userRepository;
    private final InterventionRepository interventionRepository;
    private final ModelMapper modelMapper;


    @Override
    public ReportRetrievalDTO createReport(ReportInsertionDTO reportInsertionDTO) {
        CustomUserDetails userDetails = authService.getAuthenticatedUser();
        Report report = ReportDtoMapper.toEntity(reportInsertionDTO);
        if(reportInsertionDTO.getIsSuperuser()){
            SuperUser superUser = userDetails.getSuperUser();
            if (superUser == null)
                throw new NotFoundException("SuperUser not found with id: " + userDetails.getId());
            report.setCreatedBySuperuser(superUser);
            superUser.getReports().add(report);
        }else {
            Supervisor supervisor = (Supervisor) userDetails.getUser();
            if (supervisor == null)
                throw new NotFoundException("Supervisor not found with id: " + userDetails.getId());
            report.setCreatedBySupervisor(supervisor);
            supervisor.getReportsCreated().add(report);
        }

        List<InterventionType> interventionTypes = interventionTypeRepository.findAllById(reportInsertionDTO.getInterventionTypesConcerned());
        List<Site> sites = siteRepository.findAllById(reportInsertionDTO.getSitesConcerned());
        List<User> supervisorsTechnicians = userRepository.findAllById(reportInsertionDTO.getSupervisorsTechniciansConcerned());
        List<Intervention> interventions = interventionRepository.findAllById(reportInsertionDTO.getInterventionsConcerned());

        interventions.forEach(intervention -> {
            intervention.getReports().add(report);
        });

        interventionTypes.forEach(interventionType -> {
            interventionType.getReportsConcerned().add(report);
        });

        sites.forEach(site -> {
            site.getReportsConcerned().add(report);
        });

        supervisorsTechnicians.forEach(user -> {
            user.getReportsConcerned().add(report);
        });

        report.setInterventionTypes(interventionTypes);
        report.setSites(sites);
        report.setSupervisors_technicians(supervisorsTechnicians);
        report.setInterventions(interventions);

        return ReportDtoMapper.toDto(reportRepository.save(report));
    }



    @Override
    public Page<ReportRetrievalDTO> getAllReports(List<Integer> siteIds, List<Integer> interventionTypeIds, List<Integer> userIds, LocalDateTime startDate, LocalDateTime endDate , Pageable pageable) {
        return reportRepository.findReports(siteIds , interventionTypeIds, userIds, startDate, endDate , pageable).map(ReportDtoMapper::toDto);
    }

    @Override
    public ReportRetrievalDTO getReportById(Integer id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Report not found with id: " + id));
        return ReportDtoMapper.toDto(report);
    }

    @Override
    public ReportRetrievalDTO updateReport(Integer id, ReportInsertionDTO reportInsertionDTO) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Report not found with id: " + id));

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(reportInsertionDTO, report);
        return ReportDtoMapper.toDto(reportRepository.save(report));
    }

    @Override
    public ReportRetrievalDTO deleteReport(Integer id) {
        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Report not found with id: " + id));

        report.getCreatedBySuperuser().getReports().remove(report);

        report.getCreatedBySupervisor().getReportsCreated().remove(report);

        report.getInterventions().forEach(intervention -> {
            intervention.getReports().remove(report);
        });

        report.getInterventionTypes().forEach(interventionType -> {
            interventionType.getReportsConcerned().remove(report);
        });

        report.getSites().forEach(site -> {
            site.getReportsConcerned().remove(report);
        });

        report.getSupervisors_technicians().forEach(user -> {
            user.getReportsConcerned().remove(report);
        });

        ReportRetrievalDTO reportToDelete = ReportDtoMapper.toDto(report);
        reportRepository.delete(report);
        return reportToDelete;
    }

}
