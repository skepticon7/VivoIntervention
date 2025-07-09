package org.example.backend.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@DiscriminatorValue(value = "SUPERVISOR")
@Entity
@Data
@NoArgsConstructor @SuperBuilder
public class Supervisor extends User{

}
