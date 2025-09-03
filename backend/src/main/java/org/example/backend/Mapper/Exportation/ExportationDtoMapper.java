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

    public static Exportation toEntity(ExportationInsertionDTO exportationInsertionDTO){
        if(exportationInsertionDTO == null) return null;
        return Exportation.builder()
                .fileName(exportationInsertionDTO.getFileName())
                .fileLink(exportationInsertionDTO.getFileLink())
                .build();
    }

    public static ExportationRetrievalDTO toDto(Exportation exportation){
        if(exportation == null) return null;
        return ExportationRetrievalDTO.builder()
                .id(exportation.getId())
                .createdAt(exportation.getCreatedAt())
                .updatedAt(exportation.getUpdatedAt())
                .fileName(exportation.getFileName())
                .fileLink(exportation.getFileLink())
                .exportationCreatedBy((exportation.getCreatedBySuperuser() == null && exportation.getCreatedBySupervisor() ==null) ? null : (exportation.getCreatedBySupervisor() != null ? exportation.getCreatedBySupervisor().getId() : exportation.getCreatedBySuperuser().getId()))
                .build();
    }

}
