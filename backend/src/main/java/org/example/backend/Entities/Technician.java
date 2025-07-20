package org.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.backend.Enums.TechnicianStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DiscriminatorValue(value = "TECHNICIAN")
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class Technician extends User {
    @ManyToMany(mappedBy = "technicians")
    private List<Supervisor> supervisors;
}
