package org.example.backend.ServiceImplementation;

import lombok.RequiredArgsConstructor;
import org.example.backend.DTO.InterventionType.InterventionTypeInsertionDTO;
import org.example.backend.DTO.InterventionType.InterventionTypeRetrievalDTO;
import org.example.backend.Entities.InterventionType;
import org.example.backend.Entities.SuperUser;
import org.example.backend.Exceptions.NotFoundException;
import org.example.backend.Mapper.InterventionType.InterventionTypeDtoMapper;
import org.example.backend.Repository.InterventionTypeRepository;
import org.example.backend.Repository.SuperUserRepository;
import org.example.backend.Service.InterventionTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterventionTypeServiceImplementation implements InterventionTypeService {

    private final InterventionTypeRepository interventionTypeRepository;
    private final SuperUserRepository superUserRepository;


    @Override
    public InterventionTypeRetrievalDTO createInterventionType(InterventionTypeInsertionDTO dto) {
        SuperUser superUser = superUserRepository.findById(dto.getCreatedBySuperuserId())
                .orElseThrow(() -> new NotFoundException("SuperUser not found"));

        InterventionType interventionType = InterventionType.builder()
                .interventionName(dto.getInterventionName())
                .description(dto.getDescription())
                .createdBySuperuser(superUser)
                .build();

        InterventionType saved = interventionTypeRepository.save(interventionType);

        return InterventionTypeDtoMapper.toDto(saved);
    }


    @Override
    public List<InterventionTypeRetrievalDTO> getAllInterventionTypes() {
        return interventionTypeRepository.findAll()
                .stream()
                .map(InterventionTypeDtoMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public InterventionTypeRetrievalDTO getInterventionTypeById(Integer id) {
        InterventionType interventionType = interventionTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("InterventionType not found"));
        return InterventionTypeDtoMapper.toDto(interventionType);
    }


    @Override
    public InterventionTypeRetrievalDTO updateInterventionType(Integer id, InterventionTypeInsertionDTO dto) {
        InterventionType interventionType = interventionTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("InterventionType not found"));

        interventionType.setInterventionName(dto.getInterventionName());
        interventionType.setDescription(dto.getDescription());

        InterventionType updated = interventionTypeRepository.save(interventionType);

        return InterventionTypeDtoMapper.toDto(updated);
    }


    @Override
    public InterventionTypeRetrievalDTO deleteInterventionType(Integer id) {
        InterventionType interventionType = interventionTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("InterventionType not found"));

        interventionTypeRepository.delete(interventionType);

        return InterventionTypeDtoMapper.toDto(interventionType);
    }
}
