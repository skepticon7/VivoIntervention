package org.example.backend.Repository;

import org.example.backend.Entities.Intervention;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention , Integer> {

    @Query("""
    SELECT i FROM Intervention i 
    WHERE (:siteIds IS NULL OR i.site.id IN :siteIds)
      AND (:interventionTypeIds IS NULL OR i.interventionType.id IN :interventionTypeIds)
      AND (:userIds IS NULL OR i.assignedTo.id IN :userIds)
      AND (:statuses IS NULL OR i.interventionStatus IN :statuses)
      AND (:priorities IS NULL OR i.interventionPriority IN :priorities)
      AND (:startDate IS NULL OR i.startTime >= :startDate)
      AND (:endDate IS NULL OR i.endTime <= :endDate)
    """)
    Page<Intervention> findInterventions(
            @Param("siteIds") List<Integer> siteIds,
            @Param("interventionTypeIds") List<Integer> interventionTypeIds,
            @Param("userIds") List<Integer> userIds,
            @Param("statuses") List<String> statuses,
            @Param("priorities") List<Integer> priorities,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            Pageable pageable
    );


}
