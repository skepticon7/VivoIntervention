package org.example.backend.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.backend.Enums.TechnicianStatus;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;

@DiscriminatorValue(value = "SUPERVISOR")
@Entity
@Data
@NoArgsConstructor @SuperBuilder
public class Supervisor extends User{

    @OneToMany(mappedBy = "createdBySupervisor" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> techniciansCreated;

    @OneToMany(mappedBy = "createdBySupervisor" , cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Exportation> exportationsCreated;


}
