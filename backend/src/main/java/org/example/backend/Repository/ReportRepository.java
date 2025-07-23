package org.example.backend.Repository;

import org.example.backend.Entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {}
