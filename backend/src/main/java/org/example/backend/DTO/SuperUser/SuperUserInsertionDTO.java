package org.example.backend.DTO.SuperUser;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.Utils.OnCreate;
import org.example.backend.Utils.OnUpdate;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class SuperUserInsertionDTO {
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
}
