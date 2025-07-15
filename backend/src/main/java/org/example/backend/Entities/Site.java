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
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String siteName;
    private String siteCode;
    private SiteStatus siteStatus;
    private String siteAdresse;
    private String email;
    private String phoneNumber;
    private LocalTime startOperatingHour;
    private LocalTime endOperatingHour;

    @OneToOne(optional = false)
    @JoinColumn(name = "supervisor_id", referencedColumnName = "id")
    private Supervisor supervisor;

    @OneToMany(mappedBy = "site")
    private List<Intervention> interventions = new ArrayList<>();

    @OneToMany(mappedBy = "mainSite")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<User> technicians = new ArrayList<>();




    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
