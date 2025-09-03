package org.example.backend.Service;

import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.*;
import org.example.backend.Entities.User;
import org.example.backend.Enums.TechnicianStatus;
import org.example.backend.Repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.List;

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

    SupervisorSuperuserStatsDTO getSupervisorStats(Integer id);

    TechnicianStatsDTO getTechnicianStats(Integer id);

    List<TechnicianRetrievalDTO> getAllTechincians(int page , int size);

    List<UserRetrievalDTO> getUsersForIntervention();

    UsersStatsDTO getUsersStats();

    Page<UserRetrievalDTO> findTechniciansSupervisors(String name , List<TechnicianStatus> statuses , List<String> roles , Pageable pageable);
}
