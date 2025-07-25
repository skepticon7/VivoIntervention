package org.example.backend.Repository;

import org.example.backend.Entities.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {

    @Query("""
        SELECT r FROM Report r 
        WHERE (:siteIds IS NULL OR EXISTS (SELECT s FROM r.sites s WHERE s.id IN :siteIds))
          AND (:interventionTypeIds IS NULL OR EXISTS (SELECT it FROM r.interventionTypes it WHERE it.id IN :interventionTypeIds))
          AND (:userIds IS NULL OR EXISTS (SELECT u FROM r.supervisors_technicians u WHERE u.id IN :userIds))
          AND (:startDate IS NULL OR r.createdAt >= :startDate)
          AND (:endDate IS NULL OR r.createdAt <= :endDate)
    """)
    Page<Report> findReports(
            @Param("siteIds") List<Integer> siteIds,
            @Param("interventionTypeIds") List<Integer> interventionTypeIds,
            @Param("userIds") List<Integer> userIds,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate , Pageable pageable
    );

}
