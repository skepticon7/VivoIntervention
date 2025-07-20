package org.example.backend.Repository;

import org.example.backend.Entities.SuperUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperUserRepository extends JpaRepository<SuperUser , Integer> {
}
