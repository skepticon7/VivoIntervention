package org.example.backend.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String pdfName;
    private String pdfLink;

    @ManyToOne
    @JoinColumn(name = "createdBySuperuser")
    private SuperUser createdBySuperuser;

    @ManyToOne
    @JoinColumn(name = "createdBySupervisor")
    private Supervisor createdBySupervisor;

    @Builder.Default
    @ManyToMany(mappedBy = "reportsConcerned")
    private List<User> supervisors_technicians = new ArrayList<>();

    @Builder.Default
    @ManyToMany(mappedBy = "reports")
    private List<Intervention> interventions = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
