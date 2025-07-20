package org.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuperUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    @OneToMany(mappedBy = "createdBySuperuser" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Site> sites;

    @OneToMany(mappedBy = "createdBySuperuser" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Intervention> interventions;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
    @OneToMany(mappedBy = "createdBySuperuser" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<InterventionType> interventionTypes;

    @OneToMany(mappedBy = "createdBySuperuser" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Exportation> exportations;

    @OneToMany(mappedBy = "createdBySuperuser" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Report> reports;

    @OneToMany(mappedBy = "createdBySuperuser" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<User> users;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
