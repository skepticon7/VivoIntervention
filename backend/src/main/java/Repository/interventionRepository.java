package Repository;

import org.example.backend.Entities.Intervention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface interventionRepository extends JpaRepository<Intervention,Integer> {
}
