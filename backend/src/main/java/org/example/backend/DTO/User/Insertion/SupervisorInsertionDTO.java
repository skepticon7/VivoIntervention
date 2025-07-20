package org.example.backend.DTO.User.Insertion;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

import java.time.LocalDate;
import java.util.List;

@Data @SuperBuilder @NoArgsConstructor
public class SupervisorInsertionDTO extends UserInsertionDTO {
}
