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
            name = "exportation_technician",
            joinColumns = @JoinColumn(name = "exportation_id"),
            inverseJoinColumns = @JoinColumn(name = "technician_id")
    )
    private List<User> technicians = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "exportation_intervention",
            joinColumns = @JoinColumn(name = "exportation_id"),
            inverseJoinColumns = @JoinColumn(name = "intervention_id")
    )
    private List<Intervention> interventions = new ArrayList<>();


    @ManyToOne(optional = false)
    @JoinColumn(name = "created_by_id")
    private User exportationCreatedBy;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
