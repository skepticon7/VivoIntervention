package org.example.backend.ServiceImplementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.backend.DTO.Intervention.InterventionInsertionDTO;
import org.example.backend.DTO.Intervention.InterventionRetrievalDTO;
import org.example.backend.Entities.*;
import org.example.backend.Enums.TechnicianStatus;
import org.example.backend.Exceptions.NotFoundException;
import org.example.backend.Mapper.Intervention.InterventionDtoMapper;
import org.example.backend.Repository.*;
import org.example.backend.Service.InterventionService;
import org.hibernate.annotations.NotFound;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class InterventionServiceImplementation implements InterventionService {

    private final InterventionRepository interventionRepository;
    private final InterventionTypeRepository interventionTypeRepository;
    private final SiteRepository siteRepository;
    private final SuperUserRepository superUserRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

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

        if(!(userAssignedTo.getTechnicianStatus().equals(TechnicianStatus.AVAILABLE)))
            throw new NotFoundException("User with ID " + assignedTo + " is not available for intervention assignment.");


        newIntervention.setAssignedTo(userAssignedTo);
        userAssignedTo.getInterventionsAssigned().add(newIntervention);

        newIntervention.setSite(site);
        site.getInterventions().add(newIntervention);

        newIntervention.setInterventionType(interventionType);
        interventionType.getInterventions().add(newIntervention);

        return InterventionDtoMapper.toDto(
                interventionRepository.save(newIntervention)
        );
    }

    @Override
    public InterventionRetrievalDTO getInterventionById(Integer id) {
        return InterventionDtoMapper.toDto(
                interventionRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                "Intervention with ID " + id + " not found."
                        ))
        );
    }


    @Override
    public Page<InterventionRetrievalDTO> getAllInterventions(List<Integer> siteIds, List<Integer> interventionTypeIds, List<Integer> userIds, List<String> statuses, List<Integer> priorities, LocalDate startDate, LocalDate endDate, Pageable pageable) {
        return interventionRepository.findInterventions(
                siteIds , interventionTypeIds , userIds , statuses , priorities , startDate , endDate , pageable
        ).map(InterventionDtoMapper::toDto);
    }


    @Override
    public InterventionRetrievalDTO updateIntervention(Integer id, InterventionInsertionDTO interventionInsertionDTO) {
        Intervention intervention = interventionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Intervention with ID " + id + " not found."));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(interventionInsertionDTO, intervention);
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

}
