package org.example.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"interventionName"}))
public class InterventionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String interventionName;
    private String description;

    @OneToMany(mappedBy = "interventionType", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Intervention> interventions;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "report_intervention_type",
            joinColumns = @JoinColumn(name = "interventionType_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "report_id", referencedColumnName = "id")
    )
    private List<Report> reportsConcerned;

    @ManyToOne
    @JoinColumn(name = "created_by_id", referencedColumnName = "id")
    private SuperUser createdBySuperuser;


    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
