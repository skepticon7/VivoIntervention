package org.example.backend.Repository;

import org.aspectj.apache.bcel.classfile.Module;
import org.example.backend.Entities.Exportation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExportationRepository extends JpaRepository<Exportation , Integer> {

    @Query("SELECT e FROM Exportation e WHERE LOWER(e.fileName) LIKE CONCAT('%' , :fileName , '%')")
    Optional<Exportation> findExportationByFileName(@Param("fileName") String fileName);

    @Query("SELECT e FROM Exportation e WHERE e.createdBySuperuser.id = :id")
    Page<Exportation> findExportationsBySuperuser(@Param("id") Integer id , Pageable pageable);

    @Query("SELECT e FROM Exportation e WHERE e.createdBySupervisor.id = :id")
    Page<Exportation> findExportationsBySupervisor(@Param("id") Integer id , Pageable pageable);

}
