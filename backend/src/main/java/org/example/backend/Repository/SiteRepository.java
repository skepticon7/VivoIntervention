package org.example.backend.Repository;

import org.example.backend.Entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteRepository extends JpaRepository<Site , Integer> {

}
