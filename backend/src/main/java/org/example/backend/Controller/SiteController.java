package org.example.backend.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTO.Site.SiteInsertionDTO;
import org.example.backend.DTO.Site.SiteRetrievalDTO;
import org.example.backend.Service.SiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sites")
@AllArgsConstructor
public class SiteController {

    private final SiteService siteService;

    //only exclusive to the superuser
    @PostMapping("/createSite")
    public ResponseEntity<SiteRetrievalDTO> createSite(@Valid @RequestBody SiteInsertionDTO siteInsertionDTO) {
        SiteRetrievalDTO siteRetrievalDTO = siteService.createSite(siteInsertionDTO);
        return new ResponseEntity<>(siteRetrievalDTO , HttpStatus.CREATED);
    }

}
