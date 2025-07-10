package org.example.backend.Repository;

import org.example.backend.Entities.InterventionType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterventionTypeRepository extends JpaRepository<InterventionType, Integer> {
}
