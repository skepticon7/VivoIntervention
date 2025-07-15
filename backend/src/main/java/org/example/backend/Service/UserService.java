package org.example.backend.Service;

import org.example.backend.DTO.User.Insertion.SuperUserInsertionDTO;
import org.example.backend.DTO.User.Insertion.SupervisorInsertionDTO;
import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.SuperUserRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.SupervisorRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.TechnicianRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.UserRetrievalDTO;

public interface UserService {
    UserRetrievalDTO getUserById(Integer id);

    SuperUserRetrievalDTO createSuperUser(SuperUserInsertionDTO superUserInsertionDTO);


    SuperUserRetrievalDTO getSuperUserById(Integer id);

    SupervisorRetrievalDTO getSupervisorById(Integer id);

    TechnicianRetrievalDTO getTechnicianById(Integer id);
}
