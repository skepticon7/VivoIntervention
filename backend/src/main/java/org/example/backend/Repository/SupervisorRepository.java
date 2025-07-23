package org.example.backend.Repository;

import org.example.backend.Entities.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupervisorRepository extends JpaRepository<Supervisor, Integer> {}
