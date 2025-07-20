package org.example.backend.Service;

import org.example.backend.DTO.Site.SiteInsertionDTO;
import org.example.backend.DTO.Site.SiteRetrievalDTO;

import java.util.List;

public interface SiteService {
    SiteRetrievalDTO getSiteById(Integer id);
    SiteRetrievalDTO createSite(SiteInsertionDTO siteInsertionDTO);
    List<SiteRetrievalDTO> getAllSites();
    SiteRetrievalDTO deleteSite(Integer id);
    SiteRetrievalDTO updateSite(Integer id, SiteInsertionDTO siteInsertionDTO);
}
