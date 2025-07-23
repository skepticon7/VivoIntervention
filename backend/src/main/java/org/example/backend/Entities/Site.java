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
                @UniqueConstraint(columnNames = "siteCode")
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


    @ManyToMany(mappedBy = "sites")
    private List<Exportation> exportationsConcerned;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "site_report",
            joinColumns = @JoinColumn(name = "site_id", referencedColumnName = "id"),
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
