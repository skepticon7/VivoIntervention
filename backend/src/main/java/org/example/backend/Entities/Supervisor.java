package org.example.backend.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DiscriminatorValue(value = "SUPERVISOR")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supervisor extends User{
    private String speciality;
}
