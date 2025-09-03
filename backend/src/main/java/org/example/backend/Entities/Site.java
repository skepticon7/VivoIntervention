package org.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.Enums.SiteStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
@Builder
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "siteName"),
                @UniqueConstraint(columnNames = "siteCode"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "phoneNumber"),
                @UniqueConstraint(columnNames = "siteAdresse")
        }
)

public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String siteName;
    private String siteCode;
    private String siteLocation;
    @Enumerated(EnumType.STRING)
    private SiteStatus siteStatus;
    private String siteAdresse;
    private String email;
    private String phoneNumber;
    private LocalTime startOperatingHour;
    private LocalTime endOperatingHour;

    @OneToMany(mappedBy = "site" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Intervention> interventions;

    @ManyToOne
    @JoinColumn(name = "created_by_id", referencedColumnName = "id")
    private SuperUser createdBySuperuser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
