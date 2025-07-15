package org.example.backend.ServiceImplementation;

import lombok.AllArgsConstructor;
import org.example.backend.DTO.Site.SiteInsertionDTO;
import org.example.backend.DTO.Site.SiteRetrievalDTO;
import org.example.backend.Entities.Site;
import org.example.backend.Entities.SuperUser;
import org.example.backend.Entities.Supervisor;
import org.example.backend.Entities.User;
import org.example.backend.Exceptions.NotFoundException;
import org.example.backend.Mapper.Site.SiteDtoMapper;
import org.example.backend.Mapper.User.SupervisorDtoMapper;
import org.example.backend.Repository.SiteRepository;
import org.example.backend.Repository.UserRepository;
import org.example.backend.Service.SiteService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class SiteServiceImplementation implements SiteService {

    private final SiteRepository siteRepository;
    private final UserRepository userRepository;

    @Override
    public SiteRetrievalDTO getSiteById(Integer id) {
        return null;
    }

    //specific to the super-user
    @Override
    public SiteRetrievalDTO createSite(SiteInsertionDTO siteInsertionDTO) {
        // id of logged-in user to be retrieved from spring security context instead of passing them as query params or json payload
        Integer createdById = siteInsertionDTO.getCreatedById();

        Integer supervisorId = siteInsertionDTO.getSupervisorId();

        SuperUser createdBy = (SuperUser) userRepository.findUserById(createdById)
                .orElseThrow(() -> new NotFoundException(
                        String.format("superUser with id %d not found", createdById)));

        Site newSite = SiteDtoMapper.toEntity(siteInsertionDTO);
        newSite.setCreatedBy(createdBy);
        createdBy.getSites().add(newSite);

        if (supervisorId != null) {
            Supervisor supervisor = (Supervisor) userRepository.findUserById(supervisorId)
                    .orElseThrow(() -> new NotFoundException(
                            String.format("supervisor with id %d not found", supervisorId)));
            newSite.setSupervisor(supervisor);
            supervisor.setMainSite(newSite);
        }

        Site savedSite = siteRepository.save(newSite);
        userRepository.save(createdBy);

        if(supervisorId != null)
            userRepository.save(newSite.getSupervisor());

        return SiteDtoMapper.toDto(savedSite);

    }
}
