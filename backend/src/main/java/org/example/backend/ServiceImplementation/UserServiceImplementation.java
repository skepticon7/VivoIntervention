package org.example.backend.ServiceImplementation;

import lombok.AllArgsConstructor;
import org.example.backend.DTO.User.Insertion.SuperUserInsertionDTO;
import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.SuperUserRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.SupervisorRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.TechnicianRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.UserRetrievalDTO;
import org.example.backend.Entities.User;
import org.example.backend.Exceptions.AlreadyExistsException;
import org.example.backend.Exceptions.NotFoundException;
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
        return null;
    }

    @Override
    public SuperUserRetrievalDTO createSuperUser(SuperUserInsertionDTO superUserInsertionDTO) {
        Optional<User> user = userRepository.findUserByEmail(superUserInsertionDTO.getEmail());
        if(user.isPresent())
            throw new AlreadyExistsException(String.format("User with email %s already exists", superUserInsertionDTO.getEmail()));
        return null;
    }

    @Override
    public SupervisorRetrievalDTO createSupervisor(SupervisorRetrievalDTO supervisorRetrievalDTO) {
        return null;
    }

    @Override
    public TechnicianRetrievalDTO createTechnician(TechnicianInsertionDTO technicianInsertionDTO) {
        return null;
    }

    @Override
    public SuperUserRetrievalDTO getSuperUserById(Integer id) {
        return null;
    }

    @Override
    public SupervisorRetrievalDTO getSupervisorById(Integer id) {
        return null;
    }

    @Override
    public TechnicianRetrievalDTO getTechnicianById(Integer id) {
        return null;
    }
}
