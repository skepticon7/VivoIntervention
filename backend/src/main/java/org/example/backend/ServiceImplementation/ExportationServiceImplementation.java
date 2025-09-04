package org.example.backend.ServiceImplementation;


import lombok.AllArgsConstructor;
import org.example.backend.DTO.Exportation.ExportationInsertionDTO;
import org.example.backend.DTO.Exportation.ExportationRetrievalDTO;
import org.example.backend.Entities.Exportation;
import org.example.backend.Entities.SuperUser;
import org.example.backend.Entities.Supervisor;
import org.example.backend.Exceptions.AlreadyExistsException;
import org.example.backend.Exceptions.NotFoundException;
import org.example.backend.Mapper.Exportation.ExportationDtoMapper;
import org.example.backend.Repository.ExportationRepository;
import org.example.backend.Repository.SuperUserRepository;
import org.example.backend.Repository.UserRepository;
import org.example.backend.Service.ExportationService;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ExportationServiceImplementation implements ExportationService {

    private final ExportationRepository exportationRepository;
    private final SuperUserRepository superUserRepository;
    private final UserRepository userRepository;

    @Override
    public ExportationRetrievalDTO getExportationByFileName(String fileName) {
        return ExportationDtoMapper.toDto(
                exportationRepository.findExportationByFileName(fileName)
                        .orElseThrow(() -> new NotFoundException("exportation with file name : " + fileName + " doesn't exist"))
        );
    }

    @Override
    public Page<ExportationRetrievalDTO> getExportationsByUserId(
            String role,
            Integer id,
            Pageable pageable
    ) {
        if(role.equalsIgnoreCase("SUPERUSER")){
                return exportationRepository.findExportationsBySuperuser(id , pageable)
                        .map(ExportationDtoMapper::toDto);
        }else if (role.equalsIgnoreCase("SUPERVISOR"))
            return exportationRepository.findExportationsBySupervisor(id , pageable)
                    .map(ExportationDtoMapper::toDto);
        else
            throw new RuntimeException("Invalid role");
    }

    @Override
    public ExportationRetrievalDTO getExportationById(Integer id) {
        return ExportationDtoMapper.toDto(
                exportationRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("exportation with id : " + id + " not found"))
        );
    }

    @Override
    public ExportationRetrievalDTO createExportation(ExportationInsertionDTO exportationInsertionDTO) {

        Integer createdById = exportationInsertionDTO.getCreatedBy();
        boolean isSuperUser = exportationInsertionDTO.isSuperUser();

        Exportation newExportation = ExportationDtoMapper.toEntity(exportationInsertionDTO);

        if(isSuperUser){
            SuperUser superuser = superUserRepository.findById(createdById)
                    .orElseThrow(() -> new NotFoundException("superuser with id : " + createdById + " not found"));
            superuser.getExportations().add(newExportation);
            newExportation.setCreatedBySuperuser(superuser);
        }else {
            Supervisor supervisor = userRepository.findSupervisorById(createdById)
                    .orElseThrow(() -> new NotFoundException("supervisor with id : " + createdById + " not found"));
            supervisor.getExportationsCreated().add(newExportation);
            newExportation.setCreatedBySupervisor(supervisor);
        }

        return ExportationDtoMapper.toDto(exportationRepository.save(newExportation));

    }

    @Override
    public ExportationRetrievalDTO updateExportation(Integer id, ExportationInsertionDTO exportationInsertionDTO) {
        return null;
    }

    @Override
    public ExportationRetrievalDTO deleteExportation(Integer id) {
        return null;
    }
}
