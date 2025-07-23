package org.example.backend.DTO.User.Insertion;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.backend.Utils.OnCreate;


@Data @AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class TechnicianInsertionDTO extends UserInsertionDTO{
    @NotNull(message = "is superUser cannot be null", groups = {OnCreate.class})
    private boolean isSuperUser;
}

