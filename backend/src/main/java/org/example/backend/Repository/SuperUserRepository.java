package org.example.backend.Repository;

import org.example.backend.Entities.SuperUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuperUserRepository extends JpaRepository<SuperUser, Integer> {

    Optional<SuperUser> findSuperUserByEmail(String email);

}
