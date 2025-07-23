package org.example.backend.Mapper.Exportation;


import org.aspectj.apache.bcel.classfile.Module;
import org.example.backend.DTO.Exportation.ExportationInsertionDTO;
import org.example.backend.DTO.Exportation.ExportationRetrievalDTO;
import org.example.backend.Entities.Exportation;
import org.example.backend.Entities.Intervention;
import org.example.backend.Entities.Site;
import org.example.backend.Entities.User;

import java.util.ArrayList;

public class ExportationDtoMapper {

    public Exportation toEntity(ExportationInsertionDTO exportationInsertionDTO){
        if(exportationInsertionDTO == null) return null;
        return Exportation.builder()
                .startDate(exportationInsertionDTO.getStartDate())
                .endDate(exportationInsertionDTO.getEndDate())
                .fileName(exportationInsertionDTO.getFileName())
                .fileLink(exportationInsertionDTO.getFileLink())
                .supervisors_technicians(new ArrayList<>())
                .interventions(new ArrayList<>())
                .sites(new ArrayList<>())
                .build();
    }

    public ExportationRetrievalDTO toDto(Exportation exportation){
        if(exportation == null) return null;
        return ExportationRetrievalDTO.builder()
                .id(exportation.getId())
                .createdAt(exportation.getCreatedAt())
                .updatedAt(exportation.getUpdatedAt())
                .startDate(exportation.getStartDate())
                .endDate(exportation.getEndDate())
                .fileName(exportation.getFileName())
                .fileLink(exportation.getFileLink())
                .supervisors_technicians(exportation.getSupervisors_technicians().stream().map(User::getId).toList())
                .exportationCreatedBy((exportation.getCreatedBySuperuser() == null && exportation.getCreatedBySupervisor() ==null) ? null : (exportation.getCreatedBySupervisor() != null ? exportation.getCreatedBySupervisor().getId() : exportation.getCreatedBySuperuser().getId()))
                .sites(exportation.getSites().stream().map(Site::getId).toList())
                .interventions(exportation.getInterventions().stream().map(Intervention::getId).toList())
                .build();
    }

}
