package org.example.backend.ServiceImplementation;

import lombok.AllArgsConstructor;
import org.example.backend.DTO.SuperUser.SuperUserInsertionDTO;
import org.example.backend.DTO.SuperUser.SuperUserRetrievalDTO;
import org.example.backend.Entities.SuperUser;
import org.example.backend.Exceptions.NotFoundException;
import org.example.backend.Mapper.User.SuperUserDtoMapper;
import org.example.backend.Repository.SuperUserRepository;
import org.example.backend.Service.SuperUserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SuperUserServiceImplementation implements SuperUserService {

    private final ModelMapper modelMapper;
    private final SuperUserRepository superUserRepository;

    @Override
    public SuperUserRetrievalDTO createSuperUser(SuperUserInsertionDTO superUserInsertionDTO) {
        SuperUser newSuperUser = SuperUserDtoMapper.toEntity(superUserInsertionDTO);
        return SuperUserDtoMapper.toDto(superUserRepository.save(newSuperUser));
    }

    @Override
    public SuperUserRetrievalDTO getSuperUserById(Integer id) {
        return SuperUserDtoMapper.toDto(
                superUserRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(
                                "SuperUser with id " + id + " not found"
                        ))
        );
    }
}
