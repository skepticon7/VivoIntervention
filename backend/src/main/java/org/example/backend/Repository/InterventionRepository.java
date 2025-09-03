package org.example.backend.Repository;

import org.example.backend.DTO.Intervention.InterventionsChartStats;
import org.example.backend.Entities.Intervention;
import org.example.backend.Enums.InterventionPriority;
import org.example.backend.Enums.InterventionStatus;
import org.example.backend.Enums.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention , Integer> {

    @Query("""
    SELECT i FROM Intervention i 
    WHERE (:siteIds IS NULL OR i.site.id IN :siteIds)
    AND (:types IS NULL OR i.type IN :types)
    AND (:code IS NULL OR LOWER(i.code) LIKE LOWER(CONCAT('%', :code, '%')))
    AND (:interventionTypesIds IS NULL OR i.interventionType.id IN :interventionTypesIds)
    AND (:userIds IS NULL OR i.assignedTo.id IN :userIds)
    AND (:statuses IS NULL OR i.interventionStatus IN :statuses)
    AND (:priorities IS NULL OR i.interventionPriority IN :priorities)
    AND (:startDate IS NULL OR i.startTime >= :startDate)
    AND (:endDate IS NULL OR i.startTime <= :endDate)
    """)
    Page<Intervention> findInterventions(
            @Param("code") String code,
            @Param("types") List<String> types,
            @Param("siteIds") List<Integer> siteIds,
            @Param("interventionTypesIds") List<Integer> interventionTypesIds,
            @Param("userIds") List<Integer> userIds,
            @Param("statuses") List<String> statuses,
            @Param("priorities") List<String> priorities,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            Pageable pageable
    );



    @Query("""
    SELECT i FROM Intervention i 
    WHERE (:siteIds IS NULL OR i.site.id IN :siteIds)
    AND (:types IS NULL OR i.type IN :types)
    AND (:interventionTypesIds IS NULL OR i.interventionType.id IN :interventionTypesIds)
    AND (:userIds IS NULL OR i.assignedTo.id IN :userIds)
    AND (:statuses IS NULL OR i.interventionStatus IN :statuses)
    AND (:priorities IS NULL OR i.interventionPriority IN :priorities)
    AND (:startDate IS NULL OR i.startTime >= :startDate)
    AND (:endDate IS NULL OR i.startTime <= :endDate)
    """)
    List<Intervention> findInterventionsForExportation(
            @Param("types") List<String> types,
            @Param("siteIds") List<Integer> siteIds,
            @Param("interventionTypesIds") List<Integer> interventionTypesIds,
            @Param("userIds") List<Integer> userIds,
            @Param("statuses") List<String> statuses,
            @Param("priorities") List<String> priorities,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT i FROM Intervention i ORDER BY i.createdAt DESC")
    Page<Intervention> findSuperUserLatestInterventions(Pageable pageable);

    @Query("SELECT i FROM Intervention i WHERE i.assignedTo.id = :id OR i.createdBySupervisor_technician.id = :id ORDER BY i.createdAt DESC")
    Page<Intervention> findUserLatestInterventions(@Param("id") Integer id, Pageable pageable);


    @Query("SELECT COUNT(*) FROM Intervention i WHERE i.interventionStatus = 'COMPLETED' AND (:id IS NULL OR i.assignedTo.id = :id)")
    int findCompletedInterventions(@Param("id") Integer id);

    @Query("SELECT COUNT(*) FROM Intervention i WHERE i.type = :type")
    int getInterventionCountByType(@Param("type") Type type);

    @Query("SELECT i FROM Intervention i WHERE i.startTime = :dateTime AND i.assignedTo.id = :userId")
    Optional<Intervention> checkInterventionTime(@Param("userId") Integer userId ,@Param("dateTime") LocalDateTime dateTime);

    @Query("SELECT COUNT(*) FROM Intervention i WHERE i.assignedTo.id = :id")
    Integer findAssignedInterventions(@Param("id") Integer id);

    @Query("""
    SELECT i FROM Intervention i 
    WHERE (:siteIds IS NULL OR i.site.id IN :siteIds) 
    AND i.assignedTo.id = :technicianId
        AND (:types IS NULL OR i.type IN :types)
    AND (:code IS NULL OR LOWER(i.code) LIKE LOWER(CONCAT('%', :code, '%')))
    AND (:interventionTypesIds IS NULL OR i.interventionType.id IN :interventionTypesIds)
    AND (:statuses IS NULL OR i.interventionStatus IN :statuses)
    AND (:priorities IS NULL OR i.interventionPriority IN :priorities)
    AND (:startDate IS NULL OR i.startTime >= :startDate)
    AND (:endDate IS NULL OR i.startTime <= :endDate)
    """)
    Page<Intervention> findInterventionsByUserId(
            @Param("code") String code,
            @Param("types") List<String> types,
            @Param("siteIds") List<Integer> siteIds,
            @Param("interventionTypesIds") List<Integer> interventionTypesIds,
            @Param("statuses") List<String> statuses,
            @Param("priorities") List<String> priorities,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("technicianId") Integer technicianId,
            Pageable pageable
    );

    @Query("SELECT COUNT(*) FROM Intervention i WHERE i.interventionStatus = :interventionStatus")
    Integer findInterventionsByInterventionStatus(@Param("interventionStatus") InterventionStatus interventionStatus);


    @Query("SELECT COUNT(*) FROM Intervention i WHERE i.interventionPriority = :interventionPriority")
    Integer findInterventionsByInterventionPriority(@Param("interventionPriority") InterventionPriority interventionPriority);

    @Query("SELECT i FROM Intervention i WHERE i.assignedTo.id = :id")
    List<Intervention> findAllInterventionsAssignedToUser(@Param("id") Integer id);

    @Query("""
    SELECT i 
    FROM Intervention i 
    WHERE (:id IS NULL or i.assignedTo.id = :id)
      AND i.startTime >= :startDate
      AND i.startTime < :endDate
""")
    List<Intervention> findInterventionsByUserInMonth(
            @Param("id") Integer id,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );



}
