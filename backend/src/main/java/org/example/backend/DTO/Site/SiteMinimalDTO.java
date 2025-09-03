package org.example.backend.DTO.Site;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Builder @Data
public class SiteMinimalDTO {
    private Integer id;
    private String name;
}
