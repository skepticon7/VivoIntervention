package org.example.backend.Repository;

import org.aspectj.apache.bcel.classfile.Module;
import org.example.backend.Entities.Exportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportationRepository extends JpaRepository<Exportation , Integer> {
}
