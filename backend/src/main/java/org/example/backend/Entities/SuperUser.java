package org.example.backend.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@DiscriminatorValue(value = "SUPER_USER")
@Entity
@Data
@NoArgsConstructor
@SuperBuilder
public class SuperUser extends User {

}
