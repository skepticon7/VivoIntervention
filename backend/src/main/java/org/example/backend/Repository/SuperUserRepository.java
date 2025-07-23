package org.example.backend.Repository;

import org.example.backend.Entities.SuperUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperUserRepository extends JpaRepository<SuperUser, Integer> {}
