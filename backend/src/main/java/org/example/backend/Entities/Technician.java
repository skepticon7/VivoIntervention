package org.example.backend.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@DiscriminatorValue(value = "TECHNICIAN")
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class Technician extends User {
    private String speciality;
}
