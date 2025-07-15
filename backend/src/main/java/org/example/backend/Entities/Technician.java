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


    @ManyToOne(optional = true)
    @JoinColumn(name = "supervisor_id" , nullable = true)
    private Supervisor supervisor;

    @ManyToOne
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    private Site mainSite;

    @OneToMany(mappedBy = "interventionCreatedBy")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Intervention> createdInterventions = new ArrayList<>();

    @ManyToMany
    private List<Speciality> specialities = new ArrayList<>();

    private LocalDate hireDate;


    @ManyToMany
    @JoinTable(
            name = "technician_assigned_sites",
            joinColumns = @JoinColumn(name = "technician_id"),
            inverseJoinColumns = @JoinColumn(name = "site_id")
    )
    private List<Site> assignedSites = new ArrayList<>();


    private TechnicianStatus technicianStatus;

}
