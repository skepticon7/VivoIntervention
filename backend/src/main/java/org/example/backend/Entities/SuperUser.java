package org.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@DiscriminatorValue(value = "SUPER_USER")
@Entity
@Data
@NoArgsConstructor
@SuperBuilder
public class SuperUser extends User {

    @OneToMany(mappedBy = "createdBy")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Site> sites = new ArrayList<>();


    @OneToMany(mappedBy = "createdBy")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    List<Speciality> specialities = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "createdBy")
    List<InterventionType> interventionTypes = new ArrayList<>();


    @OneToMany(mappedBy = "exportationCreatedBy")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Exportation> exportations = new ArrayList<>();

    @OneToMany(mappedBy = "reportCreatedBy")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Report> reports = new ArrayList<>();
}
