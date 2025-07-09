package org.example.backend.ServiceImplementation;

import lombok.AllArgsConstructor;
import org.example.backend.DTO.UserInsertionDTO;
import org.example.backend.DTO.UserRetrievalDTO;
import org.example.backend.Entities.User;
import org.example.backend.Exceptions.AlreadyExistsException;
import org.example.backend.Exceptions.NotFoundException;
import org.example.backend.Mapper.UserDtoMapper;
import org.example.backend.Repository.UserRepository;
import org.example.backend.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserRetrievalDTO getUserById(Integer id) {
        return UserDtoMapper.toDto(userRepository.findUserById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", id))));
    }

    @Override
    public UserRetrievalDTO createUser(UserInsertionDTO userInsertionDTO){
        Optional<User> user = userRepository.findUserByEmail(userInsertionDTO.getEmail());
        if(user.isPresent()){
            throw new AlreadyExistsException(String.format("User with email %s already exists", userInsertionDTO.getEmail()));
        }

        return UserDtoMapper.toDto(userRepository.save(
                UserDtoMapper.toEntity(userInsertionDTO)
        ));
    }
}
