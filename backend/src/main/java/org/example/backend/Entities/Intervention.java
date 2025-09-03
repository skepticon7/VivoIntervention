package org.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.apache.bcel.classfile.Module;
import org.example.backend.Enums.InterventionPriority;
import org.example.backend.Enums.InterventionStatus;
import org.example.backend.Enums.Type;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "code")})
public class Intervention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String code;
    private String comment;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private InterventionPriority interventionPriority;
    @Enumerated(EnumType.STRING)
    private InterventionStatus interventionStatus;

    @ManyToOne
    private InterventionType interventionType;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    @ManyToOne
    @JoinColumn(name = "createdBySupervisor_technician")
    private User createdBySupervisor_technician;

    @ManyToOne
    @JoinColumn(name = "createdBySuperuser")
    private SuperUser createdBySuperuser;

    @ManyToOne
    @JoinColumn(name = "assignedTo")
    private User assignedTo;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
