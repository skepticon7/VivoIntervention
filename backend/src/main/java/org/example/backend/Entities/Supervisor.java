package org.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.backend.Enums.TechnicianStatus;

import java.util.ArrayList;
import java.util.List;

@DiscriminatorValue(value = "SUPERVISOR")
@Entity
@Data
@NoArgsConstructor @SuperBuilder
public class Supervisor extends User{

    @OneToOne
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    private Site mainSite;

    @Enumerated(EnumType.STRING)
    private TechnicianStatus technicianStatus;

    @ManyToMany
    private List<Speciality> specialities = new ArrayList<>();

    @OneToMany(mappedBy = "supervisor")
    private List<Technician> team = new ArrayList<>();

    @OneToMany(mappedBy = "interventionCreatedBy")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Intervention> createdInterventions = new ArrayList<>();

    @OneToMany(mappedBy = "interventionAssignedTo")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Intervention> assignedInterventions = new ArrayList<>();

    @OneToMany(mappedBy = "exportationCreatedBy")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Exportation> exportations = new ArrayList<>();

    @OneToMany(mappedBy = "reportCreatedBy")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Report> reports = new ArrayList<>();

}
