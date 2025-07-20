package org.example.backend.Service;

import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.SupervisorRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.TechnicianRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.UserRetrievalDTO;

public interface UserService {
    UserRetrievalDTO getUserById(Integer id);

    SupervisorRetrievalDTO createSupervisor(UserInsertionDTO userInsertionDTO);

    TechnicianRetrievalDTO createTechnician(TechnicianInsertionDTO technicianInsertionDTO);

    SupervisorRetrievalDTO getSupervisorById(Integer id);

    TechnicianRetrievalDTO getTechnicianById(Integer id);

    TechnicianRetrievalDTO deleteTechnician(Integer id);

    SupervisorRetrievalDTO deleteSupervisor(Integer id);

    SupervisorRetrievalDTO updateSupervisor(Integer id , UserInsertionDTO userInsertionDTO);

    TechnicianRetrievalDTO updateTechnician(Integer id, TechnicianInsertionDTO technicianInsertionDTO);

}
