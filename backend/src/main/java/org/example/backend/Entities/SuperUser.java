package org.example.backend.Entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.Enums.Role;

import java.util.List;

@DiscriminatorValue(value = "SUPER_USER")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuperUser extends User {

}
