package org.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Exportation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private LocalDate startDate;
    private LocalDate endDate;
    private String fileName;
    private String fileLink;


    @ManyToMany
    @JoinTable(
            name = "exportation_site",
            joinColumns = @JoinColumn(name = "exportation_id" , referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "site_id", referencedColumnName = "id")
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Site> sites;


    @ManyToMany(mappedBy = "exportationsConcerned")
    private List<User> supervisors_technicians = new ArrayList<>();

    @ManyToMany(mappedBy = "exportations")
    private List<Intervention> interventions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "createdBySupervisor")
    private Supervisor createdBySupervisor;

    @ManyToOne
    @JoinColumn(name = "createdBySuperuser")
    private SuperUser createdBySuperuser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
