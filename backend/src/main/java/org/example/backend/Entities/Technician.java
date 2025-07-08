package org.example.backend.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@DiscriminatorValue(value = "TECHNICIAN")
@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Technician extends User {
    private String speciality;
}
