package org.example.backend.DTO.Site;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.internal.build.AllowNonPortable;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class SitePieStats {
    private SiteMinimalDTO site;
    private Integer interventions;
}
