package org.example.backend.DTO.Auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backend.Utils.OnCreate;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class LoginDTO {
    @NotBlank(message = "Email is required" , groups = {OnCreate.class})
    private String email;
    @NotBlank(message = "Password is required" , groups = {OnCreate.class})
    private String password;
}
