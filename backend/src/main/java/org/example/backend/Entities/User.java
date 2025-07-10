package org.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ROLE" , length = 20)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;

    private String email;

    private String password;

    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "site_id", referencedColumnName = "id")
    private Site mainSite;


    @ManyToOne
    @JoinColumn(name = "created_by" , referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User userCreatedBy;

    @OneToMany(mappedBy = "userCreatedBy")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<User> createdUsers = new ArrayList<>();

    @OneToMany(mappedBy = "exportationCreatedBy")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Exportation> exportations = new ArrayList<>();

    @OneToMany(mappedBy = "reportCreatedBy")
    private List<Report> reports = new ArrayList<>();

    @OneToMany(mappedBy = "interventionCreatedBy")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Intervention> createdInterventions = new ArrayList<>();

    @OneToMany(mappedBy = "interventionAssignedTo")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Intervention> assignedInterventions = new ArrayList<>();

    @Transient
    public String getRole() {
        DiscriminatorValue dv = this.getClass().getAnnotation(DiscriminatorValue.class);
        return dv.value();
    }

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


}



