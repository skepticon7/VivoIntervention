package org.example.backend.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTO.Site.SiteInsertionDTO;
import org.example.backend.DTO.Site.SitePieStats;
import org.example.backend.DTO.Site.SiteRetrievalDTO;
import org.example.backend.Service.SiteService;
import org.example.backend.Utils.OnCreate;
import org.example.backend.Utils.OnUpdate;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
@AllArgsConstructor
public class SiteController {

    private final SiteService siteService;

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @PostMapping("/createSite")
    public ResponseEntity<SiteRetrievalDTO> createSite(@Validated(OnCreate.class) @RequestBody SiteInsertionDTO siteInsertionDTO) {
        SiteRetrievalDTO siteRetrievalDTO = siteService.createSite(siteInsertionDTO);
        return new ResponseEntity<>(siteRetrievalDTO , HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_TECHNICIAN')")
    @GetMapping("/getSites")
    public ResponseEntity<List<SiteRetrievalDTO>> getSite(){
        List<SiteRetrievalDTO> sites = siteService.getAllSites();
        return new ResponseEntity<>(sites , HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_TECHNICIAN')")
    @GetMapping("/getSiteById/{id}")
    public ResponseEntity<SiteRetrievalDTO> getSiteById(@PathVariable("id") Integer id) {
        SiteRetrievalDTO siteRetrievalDTO = siteService.getSiteById(id);
        return new ResponseEntity<>(siteRetrievalDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @DeleteMapping("/deleteSite/{id}")
    public ResponseEntity<SiteRetrievalDTO> deleteSite(@PathVariable("id") Integer id){
        SiteRetrievalDTO deletedSite = siteService.deleteSite(id);
        return new ResponseEntity<>(deletedSite, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR')")
    @PatchMapping("/updateSite/{id}")
    public ResponseEntity<SiteRetrievalDTO> updateSite(@PathVariable("id") Integer id, @Validated(OnUpdate.class) @RequestBody SiteInsertionDTO siteInsertionDTO) {
        SiteRetrievalDTO updatedSite = siteService.updateSite(id, siteInsertionDTO);
        return new ResponseEntity<>(updatedSite, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_ROLE_SUPERUSER' , 'SCOPE_ROLE_SUPERVISOR' , 'SCOPE_ROLE_TECHNICIAN')")
    @GetMapping("/getSitesPieStats")
    public ResponseEntity<List<SitePieStats>> getSitesPieStats(
            @RequestParam(name = "id" ,required = false) Integer id
    ) {
        return new ResponseEntity<>(siteService.getSitesPieStats(id) , HttpStatus.OK);
    }


}
