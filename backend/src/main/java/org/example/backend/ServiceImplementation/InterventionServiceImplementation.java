package org.example.backend.ServiceImplementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.backend.Controller.InterventionController;
import org.example.backend.DTO.Intervention.*;
import org.example.backend.Entities.*;
import org.example.backend.Enums.InterventionPriority;
import org.example.backend.Enums.InterventionStatus;
import org.example.backend.Enums.TechnicianStatus;
import org.example.backend.Exceptions.AlreadyExistsException;
import org.example.backend.Exceptions.NotAvailableException;
import org.example.backend.Exceptions.NotFoundException;
import org.example.backend.Mapper.Intervention.InterventionDtoMapper;
import org.example.backend.Mapper.InterventionType.InterventionTypeDtoMapper;
import org.example.backend.Mapper.Site.SiteDtoMapper;
import org.example.backend.Mapper.User.UserDtoMapper;
import org.example.backend.Repository.*;
import org.example.backend.Service.InterventionService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.modelmapper.Converters.Collection.map;

@Slf4j
@Service
@AllArgsConstructor
public class InterventionServiceImplementation implements InterventionService {

    private final InterventionRepository interventionRepository;
    private final InterventionTypeRepository interventionTypeRepository;
    private final SiteRepository siteRepository;
    private final SuperUserRepository superUserRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ExportationRepository exportationRepository;

    @Transactional
    @Override
    public InterventionRetrievalDTO createIntervention(InterventionInsertionDTO interventionInsertionDTO) {
        Integer siteId = interventionInsertionDTO.getSite();
        Integer interventionTypeId = interventionInsertionDTO.getInterventionType();
        Integer createdBy = interventionInsertionDTO.getCreatedBy();
        Integer assignedTo = interventionInsertionDTO.getAssignedTo();

        Intervention newIntervention = InterventionDtoMapper.toEntity(interventionInsertionDTO);

        InterventionType interventionType = interventionTypeRepository.findById(interventionTypeId)
                .orElseThrow(() -> new NotFoundException(
                        "Intervention Type with ID " + interventionInsertionDTO.getInterventionType() + " not found."
                ));

        Site site = siteRepository.findById(siteId)
                .orElseThrow(() -> new NotFoundException(
                        "Site with ID " + interventionInsertionDTO.getSite() + " not found."
                ));

        if(interventionInsertionDTO.getIsSuperUser()){
            SuperUser superUser = superUserRepository.findById(createdBy)
                    .orElseThrow(() -> new NotFoundException(
                            "SuperUser with ID " + createdBy + " not found."
                    ));
            newIntervention.setCreatedBySuperuser(superUser);
            superUser.getInterventions().add(newIntervention);
        } else {
            User user = userRepository.findUserById(createdBy)
                    .orElseThrow(() -> new NotFoundException(
                            "Supervisor with ID " + createdBy + " not found."
                    ));
            newIntervention.setCreatedBySupervisor_technician(user);
            user.getInterventionsCreated().add(newIntervention);
        }

        User userAssignedTo = userRepository.findUserById(assignedTo)
                        .orElseThrow(() -> new NotFoundException(
                                "User to assign intervention to with ID " + assignedTo + " not found."
                        ));

        if(userAssignedTo.getTechnicianStatus().equals(TechnicianStatus.ON_LEAVE))
            throw new NotAvailableException(String.format("%s is on leave and not available for intervention assignment." , userAssignedTo.getFirstName().concat(" ").concat(userAssignedTo.getLastName())));


        Optional<Intervention> existingIntervention = interventionRepository.checkInterventionTime(userAssignedTo.getId() , newIntervention.getStartTime());
        if(existingIntervention.isPresent()) {
            throw new NotAvailableException(String.format("%s already has an intervention at this time." , userAssignedTo.getFirstName().concat(" ").concat(userAssignedTo.getLastName())));
        }


        int typeCount = interventionRepository.getInterventionCountByType(newIntervention.getType());
        System.out.println(typeCount);
        newIntervention.setCode(newIntervention.getType().name().substring(0, 3).toUpperCase() + "-" + newIntervention.getId());

        newIntervention.setAssignedTo(userAssignedTo);
        userAssignedTo.getInterventionsAssigned().add(newIntervention);

        newIntervention.setSite(site);
        site.getInterventions().add(newIntervention);

        newIntervention.setInterventionType(interventionType);
        interventionType.getInterventions().add(newIntervention);

        newIntervention = interventionRepository.save(newIntervention);

        newIntervention.setCode(newIntervention.getType().toString().substring(0, 3).toUpperCase() + "-" + newIntervention.getId());



        return InterventionDtoMapper.toDto(
                interventionRepository.save(newIntervention)
        );
    }

    @Override
    public InterventionDTO getInterventionById(Integer id) {
                Intervention intervention =  interventionRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                "Intervention with ID " + id + " not found."
                        ));
                return InterventionDTO.builder()
                .id(intervention.getId())
                        .createdAt(intervention.getCreatedAt())
                        .updatedAt(intervention.getUpdatedAt())
                .type(intervention.getType().toString())
                .startDate(intervention.getStartTime())
                .endDate(intervention.getEndTime())
                .comment(intervention.getComment())
                .interventionStatus(intervention.getInterventionStatus().toString())
                .interventionType(InterventionTypeDtoMapper.toDto(intervention.getInterventionType()))
                .site(SiteDtoMapper.toDto(intervention.getSite()))
                .interventionPriority(intervention.getInterventionPriority().toString())
                .interventionAssignedTo(UserDtoMapper.toDto(intervention.getAssignedTo()))
                .build();


    }


    @Override
    public Page<InterventionRetrievalDTO> getAllInterventions(String code , List<String> types , List<Integer> siteIds, List<Integer> interventionTypeIds, List<Integer> userIds, List<String> statuses, List<String> priorities, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return interventionRepository.findInterventions(
                code , types ,siteIds , interventionTypeIds , userIds , statuses , priorities , startDate , endDate , pageable
        ).map(InterventionDtoMapper::toDto);
    }

    @Override
    public List<InterventionRetrievalDTO> getInterventionsForExportation(String fileName, List<String> types, List<Integer> siteIds, List<Integer> interventionTypeIds, List<Integer> userIds, List<String> statuses, List<String> priorities, LocalDateTime startDate, LocalDateTime endDate) {
        Optional<Exportation> exportationCheck = exportationRepository.findExportationByFileName(fileName);
        if (exportationCheck.isPresent()) {
            throw new AlreadyExistsException("Exportation with file name : " + fileName + " already exists");
        }
        return interventionRepository.findInterventionsForExportation(
                 types ,siteIds , interventionTypeIds , userIds , statuses , priorities , startDate , endDate
        ).stream().map(InterventionDtoMapper::toDto).toList();
    }


    @Override
    public InterventionRetrievalDTO updateIntervention(Integer id, InterventionInsertionDTO interventionInsertionDTO) {
        Intervention intervention = interventionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Intervention with ID " + id + " not found."));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(interventionInsertionDTO, intervention);
        if(interventionInsertionDTO.getSite() != null) {
            Site site = siteRepository.findById(interventionInsertionDTO.getSite())
                    .orElseThrow(() -> new NotFoundException("site with id : " + interventionInsertionDTO.getSite() + " not found"));
            intervention.getSite().getInterventions().remove(intervention);
            intervention.setSite(site);
            site.getInterventions().add(intervention);
        }

        if(interventionInsertionDTO.getInterventionType() !=  null) {
            InterventionType interventionType = interventionTypeRepository.findById(interventionInsertionDTO.getInterventionType())
                    .orElseThrow(() -> new NotFoundException("intervention type with id : " + id + " not found"));
            intervention.setInterventionType(interventionType);
        }

        if(interventionInsertionDTO.getAssignedTo() != null) {
            User user = userRepository.findById(interventionInsertionDTO.getAssignedTo())
                    .orElseThrow(() -> new NotFoundException("technician or supervisor with id : " + id + " not found"));
            intervention.getAssignedTo().getInterventionsAssigned().remove(intervention);
            intervention.setAssignedTo(user);
            user.getInterventionsAssigned().add(intervention);
        }



        intervention.setCode(intervention.getType().toString().substring(0, 3).toUpperCase() + "-" + intervention.getId());
        return InterventionDtoMapper.toDto(
                interventionRepository.save(intervention)
        );
    }

    @Transactional
    @Override
    public InterventionRetrievalDTO deleteIntervention(Integer id) {
        Intervention intervention = interventionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Intervention with ID " + id + " not found."));

        Intervention interventionToDelete = interventionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Intervention with ID " + id + " not found."));

        if (intervention.getCreatedBySuperuser() != null) {
            intervention.getCreatedBySuperuser().getInterventions().remove(intervention);
        }

        if (intervention.getCreatedBySupervisor_technician() != null) {
            intervention.getCreatedBySupervisor_technician().getInterventionsCreated().remove(intervention);
            intervention.getCreatedBySupervisor_technician().getInterventionsAssigned().remove(intervention);
        }

        if (intervention.getAssignedTo() != null) {
            intervention.getAssignedTo().getInterventionsAssigned().remove(intervention);
        }

        if (intervention.getSite() != null) {
            intervention.getSite().getInterventions().remove(intervention);
        }

        if (intervention.getInterventionType() != null) {
            intervention.getInterventionType().getInterventions().remove(intervention);
        }

        interventionRepository.delete(intervention);

        return InterventionDtoMapper.toDto(interventionToDelete);
    }

    @Override
    public List<InterventionDashboard> getUserLatestInterventions(Integer id)  {
        userRepository.findUserById(id)
                .orElseThrow(() -> new NotFoundException("User with ID " + id + " not found."));
        return interventionRepository.findUserLatestInterventions(id , PageRequest.of(0 , 5)).stream()
                .map(inter -> {
                  return  InterventionDashboard.builder()
                            .id(inter.getId())
                          .code(inter.getCode())
                            .type(inter.getType().toString())
                            .status(inter.getInterventionStatus().toString())
                            .interventionType(inter.getInterventionType().getName())
                            .priority(inter.getInterventionPriority().toString())
                            .site(inter.getSite().getSiteName())
                            .technician(inter.getAssignedTo().getFirstName().concat(" ").concat(inter.getAssignedTo().getLastName()))
                            .startTime(inter.getStartTime())
                            .build();
                }
                ).toList();
    }

    @Override
    public List<InterventionDashboard> getSuperuserLatestInterventions() {
        return interventionRepository.findSuperUserLatestInterventions(PageRequest.of(0 , 5)).stream()
                .map(inter -> {
                            return  InterventionDashboard.builder()
                                    .id(inter.getId())
                                    .code(inter.getCode())
                                    .type(inter.getType().toString())
                                    .status(inter.getInterventionStatus().toString())
                                    .interventionType(inter.getInterventionType().getName())
                                    .priority(inter.getInterventionPriority().toString())
                                    .site(inter.getSite().getSiteName())
                                    .technician(inter.getAssignedTo().getFirstName().concat(" ").concat(inter.getAssignedTo().getLastName()))
                                    .startTime(inter.getStartTime())
                                    .build();
                        }
                ).toList();
    }

    @Override
    public Page<InterventionRetrievalDTO> getInterventionsByUserId(Integer technicianId , String code , List<String> types ,  List<Integer> siteIds, List<Integer> interventionTypeIds, List<String> statuses, List<String> priorities, LocalDateTime startDate, LocalDateTime endDate , Pageable pageable) {
        userRepository.findTechnicianById(technicianId)
                .orElseThrow(() -> new NotFoundException("technician with id " + technicianId + " not found"));
        return interventionRepository.findInterventionsByUserId(
                code, types, siteIds, interventionTypeIds , statuses , priorities , startDate , endDate , technicianId , pageable
        ).map(InterventionDtoMapper::toDto);
    }
    @Override
    public InterventionsStats getInterventionsStats(String role , Integer id) {
        List<Intervention> allInterventions = new ArrayList<>();
        if(role.equals("TECHNICIAN")){
            userRepository.findTechnicianById(id)
                    .orElseThrow(() -> new NotFoundException("Technician with id : " + id + " not found"));
            allInterventions = interventionRepository.findAllInterventionsAssignedToUser(id);
        }else {
            allInterventions = interventionRepository.findAll();
        }

        long total = allInterventions.size();
        Integer urgent = (int) allInterventions.stream().filter(inter -> inter.getInterventionPriority().equals(InterventionPriority.URGENT)).count();
        Integer completed = (int) allInterventions.stream().filter(inter -> inter.getInterventionStatus().equals(InterventionStatus.COMPLETED)).count();
        Integer inProgress = (int) allInterventions.stream().filter(inter -> inter.getInterventionStatus().equals(InterventionStatus.IN_PROGRESS)).count();
        return InterventionsStats.builder()
                .total((int)total)
                .urgent(urgent)
                .completed(completed)
                .inProgress( inProgress)
                .build();
    }

    @Override
    public List<InterventionsChartStats> getInterventionsChartStats(Integer id) {
        if (id != null) {
            userRepository.findUserById(id)
                    .orElseThrow(() -> new NotFoundException("user with id : " + id + " not found"));
        }

        List<InterventionsChartStats> stats = new ArrayList<>();
        int currentYear = LocalDate.now().getYear();

        for (int month = 1; month <= 12; month++) {
            LocalDate startDate = LocalDate.of(currentYear, month, 1);

            LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

            String mon = startDate.format(DateTimeFormatter.ofPattern("MMM"));

            List<Intervention> interventions = interventionRepository.findInterventionsByUserInMonth(
                    id,
                    startDate.atStartOfDay(),
                    endDate.atTime(LocalTime.MAX)
            );

            InterventionsChartStats interventionStat = InterventionsChartStats.builder()
                    .month(mon)
                    .completed((int) interventions.stream()
                            .filter(inter -> inter.getInterventionStatus().equals(InterventionStatus.COMPLETED))
                            .count())
                    .in_progress((int) interventions.stream()
                            .filter(inter -> inter.getInterventionStatus().equals(InterventionStatus.IN_PROGRESS))
                            .count())
                    .scheduled((int) interventions.stream()
                            .filter(inter -> inter.getInterventionStatus().equals(InterventionStatus.SCHEDULED))
                            .count())
                    .build();

            stats.add(interventionStat);
        }

        return stats;
    }

}
