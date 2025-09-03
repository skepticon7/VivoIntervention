package org.example.backend.Repository;

import lombok.AllArgsConstructor;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.UserRetrievalDTO;
import org.example.backend.Entities.Supervisor;
import org.example.backend.Entities.Technician;
import org.example.backend.Entities.User;
import org.example.backend.Enums.TechnicianStatus;
import org.hibernate.sql.ast.tree.from.LazyTableGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findUserById(@Param("id") Integer id);

    @Query("SELECT u FROM Technician u WHERE u.id = :id")
    Optional<Technician> findTechnicianById(Integer id);

    @Query("SELECT u FROM Supervisor u WHERE u.id = :id")
    Optional<Supervisor> findSupervisorById(Integer id);

    @Query("SELECT u FROM User u WHERE u.class = Technician")
    Page<Technician> findAllTechnicians(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.class = Supervisor ")
    List<Supervisor> findAllSupervisors();

    Optional<User> findUserByEmail(String email);

    @Query("SELECT COUNT(*) FROM User u WHERE u.technicianStatus = 'AVAILABLE' ")
    int findAvailableUsers();

    @Query("""
        SELECT u FROM User u 
       WHERE (:name IS NULL OR LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%'))
              OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :name, '%')))
       AND (:statuses IS NULL OR u.technicianStatus IN :statuses)
       AND (:roles IS NULL OR TYPE(u) IN :roles)
       
""")
    Page<User> findAllTechniciansSupervisors(
            @Param("name") String name ,
            @Param("statuses") List<TechnicianStatus> statuses,
            @Param("roles") List<Class<? extends User>> roles,
            Pageable pageable
            );


}
