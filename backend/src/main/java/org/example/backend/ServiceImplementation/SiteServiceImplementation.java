package org.example.backend.ServiceImplementation;

import org.example.backend.DTO.Site.SiteInsertionDTO;
import org.example.backend.DTO.Site.SiteRetrievalDTO;
import org.example.backend.Service.SiteService;
import org.springframework.stereotype.Service;


@Service
public class SiteServiceImplementation implements SiteService {
    @Override
    public SiteRetrievalDTO getSiteById(Integer id) {
        return null;
    }

    @Override
    public SiteRetrievalDTO createSite(SiteInsertionDTO siteInsertionDTO) {
        return null;
    }
}
