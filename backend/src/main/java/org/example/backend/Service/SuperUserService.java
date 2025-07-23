package org.example.backend.Service;

import org.example.backend.DTO.SuperUser.SuperUserInsertionDTO;
import org.example.backend.DTO.SuperUser.SuperUserRetrievalDTO;

public interface SuperUserService {
    SuperUserRetrievalDTO createSuperUser(SuperUserInsertionDTO superUserInsertionDTO);
    SuperUserRetrievalDTO getSuperUserById(Integer id);
    SuperUserRetrievalDTO updateSuperUser(Integer id, SuperUserInsertionDTO superUserInsertionDTO);
}
