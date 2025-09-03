package org.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.aspectj.apache.bcel.classfile.Module;
import org.example.backend.Enums.TechnicianStatus;
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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "email") , @UniqueConstraint(columnNames = "phoneNumber") })
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate hireDate;

    @Enumerated(EnumType.STRING)
    private TechnicianStatus technicianStatus;

    @ManyToOne
    private SuperUser createdBySuperuser;

    @ManyToOne
    private Supervisor createdBySupervisor;

    @OneToMany(mappedBy = "assignedTo" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Intervention> interventionsAssigned;

    @OneToMany(mappedBy = "createdBySupervisor_technician" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Intervention> interventionsCreated;



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



