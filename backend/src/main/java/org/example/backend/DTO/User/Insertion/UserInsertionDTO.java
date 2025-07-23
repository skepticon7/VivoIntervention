package org.example.backend.DTO.User.Insertion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.backend.Utils.OnCreate;
import org.example.backend.Utils.OnUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class UserInsertionDTO {
    @NotBlank(message = "First name cannot be blank" , groups = {OnCreate.class})
    private String firstName;
    @NotBlank(message = "Last name cannot be blank" , groups = {OnCreate.class})
    private String lastName;
    @NotBlank(message = "Email cannot be blank" , groups = {OnCreate.class})
    private String email;
    @NotBlank(message = "Phone number cannot be blank" , groups = {OnCreate.class})
    private String phoneNumber;
    @NotBlank(message = "Password cannot be blank" , groups = {OnCreate.class})
    @Size(min = 8 , max = 20 , message = "Password must be between 8 and 20 characters" , groups = {OnCreate.class , OnUpdate.class})
    private String password;
    @NotBlank(message = "Technician status cannot be blank" , groups = {OnCreate.class})
    private String technicianStatus;
    @NotNull(message = "Hire date cannot be blank" , groups = {OnCreate.class})
    @Past(message = "Hire date must be a past date" , groups = {OnCreate.class})
    private LocalDate hireDate;
    @NotNull(message = "Created by cannot be null" , groups = {OnCreate.class})
    private Integer createdBy;
}
