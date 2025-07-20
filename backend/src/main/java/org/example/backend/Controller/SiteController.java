package org.example.backend.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.backend.DTO.Site.SiteInsertionDTO;
import org.example.backend.DTO.Site.SiteRetrievalDTO;
import org.example.backend.Service.SiteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getSites")
    public ResponseEntity<List<SiteRetrievalDTO>> getSite(){
        List<SiteRetrievalDTO> sites = siteService.getAllSites();
        return new ResponseEntity<>(sites , HttpStatus.OK);
    }

    @GetMapping("/getSiteById/{id}")
    public ResponseEntity<SiteRetrievalDTO> getSiteById(@PathVariable("id") Integer id) {
        SiteRetrievalDTO siteRetrievalDTO = siteService.getSiteById(id);
        return new ResponseEntity<>(siteRetrievalDTO, HttpStatus.OK);
    }

    //only exclusive to the superuser
    @DeleteMapping("/deleteSite/{id}")
    public ResponseEntity<SiteRetrievalDTO> deleteSite(@PathVariable("id") Integer id){
        SiteRetrievalDTO deletedSite = siteService.deleteSite(id);
        return new ResponseEntity<>(deletedSite, HttpStatus.OK);
    }

    //only exclusive to the superuser
    @PatchMapping("/updateSite/{id}")
    public ResponseEntity<SiteRetrievalDTO> updateSite(@PathVariable("id") Integer id, @Valid @RequestBody SiteInsertionDTO siteInsertionDTO) {
        SiteRetrievalDTO updatedSite = siteService.updateSite(id, siteInsertionDTO);
        return new ResponseEntity<>(updatedSite, HttpStatus.OK);
    }

}
