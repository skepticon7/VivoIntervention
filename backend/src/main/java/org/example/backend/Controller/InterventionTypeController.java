package org.example.backend.Controller;

import lombok.AllArgsConstructor;
import org.example.backend.Service.InterventionTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interventionType")
@AllArgsConstructor
public class InterventionTypeController {
    private final InterventionTypeService interventionTypeService;

}
