package org.example.backend.Repository;

import org.example.backend.Entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteRepository extends JpaRepository<Site , Integer> {
    Optional<Site> findSiteBySiteNameOrSiteCode(String siteName, String siteCode);

    @Query("SELECT COUNT(*) FROM Site s WHERE s.siteStatus = 'ACTIVE' ")
    int findActiveSites();



}
