package org.example.backend.Repository;

import lombok.AllArgsConstructor;
import org.example.backend.Entities.Supervisor;
import org.example.backend.Entities.Technician;
import org.example.backend.Entities.User;
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
    List<Technician> findAllTechnicians();

    @Query("SELECT u FROM User u WHERE u.class = Supervisor ")
    List<Supervisor> findAllSupervisors();

    Optional<User> findUserByEmail(String email);
}
