package org.example.backend.ServiceImplementation;

import lombok.AllArgsConstructor;
import org.example.backend.DTO.SuperUser.SuperUserInsertionDTO;
import org.example.backend.DTO.SuperUser.SuperUserRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.SupervisorSuperuserStatsDTO;
import org.example.backend.Entities.SuperUser;
import org.example.backend.Exceptions.NotFoundException;
import org.example.backend.Mapper.User.SuperUserDtoMapper;
import org.example.backend.Repository.InterventionRepository;
import org.example.backend.Repository.SiteRepository;
import org.example.backend.Repository.SuperUserRepository;
import org.example.backend.Repository.UserRepository;
import org.example.backend.Service.SuperUserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SuperUserServiceImplementation implements SuperUserService {

    private final ModelMapper modelMapper;
    private final SuperUserRepository superUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final InterventionRepository interventionRepository;
    private final UserRepository userRepository;
    private final SiteRepository siteRepository;

    @Override
    public SuperUserRetrievalDTO createSuperUser(SuperUserInsertionDTO superUserInsertionDTO) {
        SuperUser newSuperUser = SuperUserDtoMapper.toEntity(superUserInsertionDTO);
        newSuperUser.setPassword(passwordEncoder.encode(superUserInsertionDTO.getPassword()));
        return SuperUserDtoMapper.toDto(superUserRepository.save(newSuperUser));
    }

    @Override
    public SuperUserRetrievalDTO getSuperUserById(Integer id) {
        return SuperUserDtoMapper.toDto(
                superUserRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                "SuperUser with id " + id + " not found"
                        ))
        );
    }

    @Override
    public SuperUserRetrievalDTO updateSuperUser(Integer id, SuperUserInsertionDTO superUserInsertionDTO) {
        SuperUser superUser = superUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "SuperUser with id " + id + " not found"
                ));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(superUserInsertionDTO, superUser);
        return SuperUserDtoMapper.toDto(
                superUserRepository.save(superUser)
        );
    }

    @Override
    public SupervisorSuperuserStatsDTO getSuperuserStats(Integer id) {
        superUserRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("superuser with id " + id + " not found"));
        return SupervisorSuperuserStatsDTO.builder()
                .completedInterventions(interventionRepository.findCompletedInterventions(null))
                .activeSites(siteRepository.findActiveSites())
                .availableUsers(userRepository.findAvailableUsers())
                .build();
    }
}
