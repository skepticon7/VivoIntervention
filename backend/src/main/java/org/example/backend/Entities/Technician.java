package org.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.backend.Enums.TechnicianStatus;

import java.util.ArrayList;
import java.util.List;

@DiscriminatorValue(value = "TECHNICIAN")
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class Technician extends User {

    private String speciality;

    @ManyToOne(optional = false)
    @JoinColumn(name = "supervisor_id")
    private Supervisor supervisor;

    @ManyToMany
    @JoinTable(
            name = "technician_assigned_sites",
            joinColumns = @JoinColumn(name = "technician_id"),
            inverseJoinColumns = @JoinColumn(name = "site_id")
    )
    private List<Site> assignedSites = new ArrayList<>();

    private TechnicianStatus technicianStatus;

}
