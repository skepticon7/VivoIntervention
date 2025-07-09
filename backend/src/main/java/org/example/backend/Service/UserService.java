package org.example.backend.Service;

import org.example.backend.DTO.UserInsertionDTO;
import org.example.backend.DTO.UserRetrievalDTO;
import org.example.backend.Entities.User;

public interface UserService {
    UserRetrievalDTO getUserById(Integer id);

    UserRetrievalDTO createUser(UserInsertionDTO userInsertionDTO);
}
