package org.example.backend.ServiceImplementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.backend.DTO.Site.SiteInsertionDTO;
import org.example.backend.DTO.Site.SiteRetrievalDTO;
import org.example.backend.Entities.Site;
import org.example.backend.Entities.SuperUser;
import org.example.backend.Entities.Supervisor;
import org.example.backend.Entities.User;
import org.example.backend.Exceptions.AlreadyExistsException;
import org.example.backend.Exceptions.NotFoundException;
import org.example.backend.Mapper.Site.SiteDtoMapper;
import org.example.backend.Mapper.User.SupervisorDtoMapper;
import org.example.backend.Repository.SiteRepository;
import org.example.backend.Repository.SuperUserRepository;
import org.example.backend.Repository.UserRepository;
import org.example.backend.Service.SiteService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional
public class SiteServiceImplementation implements SiteService {

    private final SiteRepository siteRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final SuperUserRepository superUserRepository;

    @Override
    public SiteRetrievalDTO getSiteById(Integer id) {
        Optional<Site> site = siteRepository.findById(id);
        if (site.isEmpty()) {
            throw new NotFoundException(String.format("Site with id %d not found", id));
        }
        return SiteDtoMapper.toDto(site.get());
    }

    //specific to the super-user
    @Override
    public SiteRetrievalDTO createSite(SiteInsertionDTO siteInsertionDTO) {
        Integer createdBy = siteInsertionDTO.getCreatedById();

        SuperUser superUser = superUserRepository.findById(createdBy)
                .orElseThrow(() -> new NotFoundException("SuperUser with id " + createdBy + " not found"));


        Optional<Site> siteCheck = siteRepository.findSiteBySiteNameOrSiteCode(siteInsertionDTO.getSiteName(), siteInsertionDTO.getSiteCode());

        if(siteCheck.isPresent()) {
            throw new AlreadyExistsException("Site with name " + siteInsertionDTO.getSiteName() + " or code " + siteInsertionDTO.getSiteCode() + " already exists");
        }
        Site newSite = SiteDtoMapper.toEntity(siteInsertionDTO);

        List<User> users = userRepository.findAll();

        superUser.getSites().add(newSite);
        newSite.setCreatedBySuperuser(superUser);



        return SiteDtoMapper.toDto(siteRepository.save(newSite));
    }

    @Override
    public List<SiteRetrievalDTO> getAllSites() {
        return siteRepository.findAll().stream()
                .map(SiteDtoMapper::toDto)
                .toList();
    }

    @Override
    public SiteRetrievalDTO deleteSite(Integer id) {

        Site siteCheck = siteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Site with id " + id + " not found"));

        Site site = siteRepository.getReferenceById(id);



        siteRepository.delete(site);

        return SiteDtoMapper.toDto(siteCheck);

    }

    //to discuss with my internship supervisor
    @Override
    public SiteRetrievalDTO updateSite(Integer id, SiteInsertionDTO siteInsertionDTO) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Site with id " + id + " not found"));

        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.map(siteInsertionDTO, site);

        return SiteDtoMapper.toDto(siteRepository.save(site));
    }
}
