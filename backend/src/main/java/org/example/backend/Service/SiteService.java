package org.example.backend.Service;

import org.example.backend.DTO.Site.SiteInsertionDTO;
import org.example.backend.DTO.Site.SiteRetrievalDTO;

public interface SiteService {
    SiteRetrievalDTO getSiteById(Integer id);
    SiteRetrievalDTO createSite(SiteInsertionDTO siteInsertionDTO);
}
