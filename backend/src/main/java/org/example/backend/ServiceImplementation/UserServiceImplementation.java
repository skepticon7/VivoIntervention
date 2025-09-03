package org.example.backend.ServiceImplementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.*;
import org.example.backend.Entities.*;
import org.example.backend.Enums.TechnicianStatus;
import org.example.backend.Exceptions.AlreadyExistsException;
import org.example.backend.Exceptions.NotFoundException;
import org.example.backend.Mapper.User.SupervisorDtoMapper;
import org.example.backend.Mapper.User.TechnicianDtoMapper;
import org.example.backend.Mapper.User.UserDtoMapper;
import org.example.backend.Repository.*;
import org.example.backend.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final SiteRepository siteRepository;
    private final InterventionRepository interventionRepository;
    private final InterventionTypeRepository interventionTypeRepository;
    private final SuperUserRepository superUserRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserRetrievalDTO getUserById(Integer id) {
        return null;
    }

    @Transactional
    @Override
    public SupervisorRetrievalDTO createSupervisor(UserInsertionDTO userInsertionDTO) {
        Optional<User> userCheck = userRepository.findUserByEmail(userInsertionDTO.getEmail());
        if(userCheck.isPresent())
            throw new AlreadyExistsException("User with email " + userInsertionDTO.getEmail() + " already exists");
        Supervisor supervisor = SupervisorDtoMapper.toEntity(userInsertionDTO);
        Integer createdById = userInsertionDTO.getCreatedBy();

        if(userInsertionDTO.isSuperUser()){
            SuperUser superUser = superUserRepository.findById(createdById)
                    .orElseThrow(() -> new NotFoundException("SuperUser with id " + userInsertionDTO.getCreatedBy() + " not found"));
            supervisor.setCreatedBySuperuser(superUser);
            superUser.getUsers().add(supervisor);

        } else {
            Supervisor supervisorCreator = (Supervisor) userRepository.findUserById(createdById)
                    .orElseThrow(() -> new NotFoundException("Supervisor with id " + userInsertionDTO.getCreatedBy() + " not found"));
            supervisor.setCreatedBySupervisor(supervisor);
            supervisorCreator.getTechniciansCreated().add(supervisor);
        }

        supervisor.setPassword(passwordEncoder.encode(userInsertionDTO.getPassword()));

        return SupervisorDtoMapper.toDto(
                userRepository.save(supervisor)
        );
    }

    @Transactional
    @Override
    public TechnicianRetrievalDTO createTechnician(TechnicianInsertionDTO technicianInsertionDTO) {
        System.out.println(technicianInsertionDTO.isSuperUser());
        Optional<User> userCheck = userRepository.findUserByEmail(technicianInsertionDTO.getEmail());

        if(userCheck.isPresent())
            throw new AlreadyExistsException("User with email " + technicianInsertionDTO.getEmail() + " already exists");

        Technician technician = TechnicianDtoMapper.toEntity(technicianInsertionDTO);
        technician.setPassword(passwordEncoder.encode(technicianInsertionDTO.getPassword()));
        Integer createdById = technicianInsertionDTO.getCreatedBy();

        if(technicianInsertionDTO.isSuperUser()){
            SuperUser superUser = superUserRepository.findById(createdById)
                    .orElseThrow(() -> new NotFoundException("SuperUser with id " + technicianInsertionDTO.getCreatedBy() + " not found"));
            technician.setCreatedBySuperuser(superUser);
            superUser.getUsers().add(technician);

        } else {
            Supervisor supervisor = (Supervisor) userRepository.findUserById(createdById)
                    .orElseThrow(() -> new NotFoundException("Supervisor with id " + technicianInsertionDTO.getCreatedBy() + " not found"));
            technician.setCreatedBySupervisor(supervisor);
            supervisor.getTechniciansCreated().add(technician);
        }
        return TechnicianDtoMapper.toDto(
                userRepository.save(technician)
        );

    }

    @Override
    public SupervisorRetrievalDTO getSupervisorById(Integer id) {
        return SupervisorDtoMapper.toDto(
                userRepository.findSupervisorById(id)
                        .orElseThrow(() -> new NotFoundException("Supervisor with id " + id + " not found"))
        );
    }

    @Override
    public TechnicianRetrievalDTO getTechnicianById(Integer id) {
        return TechnicianDtoMapper.toDto(
                userRepository.findTechnicianById(id)
                        .orElseThrow(() -> new NotFoundException("Technician with id " + id + " not found"))
        );
    }

    @Transactional
    @Override
    public TechnicianRetrievalDTO deleteTechnician(Integer id) {
        Technician technician = userRepository.findTechnicianById(id)
                .orElseThrow(() -> new NotFoundException("Technician with id " + id + " not found"));

        if(technician.getCreatedBySupervisor() != null) {
            technician.getCreatedBySupervisor().getTechniciansCreated().remove(technician);
        } else if (technician.getCreatedBySuperuser() != null) {
            technician.getCreatedBySuperuser().getUsers().remove(technician);
        }

        userRepository.delete(technician);
        return TechnicianDtoMapper.toDto(technician);
    }

    @Transactional
    @Override
    public SupervisorRetrievalDTO deleteSupervisor(Integer id) {
        Supervisor supervisor = userRepository.findSupervisorById(id)
                .orElseThrow(() -> new NotFoundException("Supervisor with id " + id + " not found"));


        supervisor.getTechniciansCreated().forEach(technician -> {
            technician.setCreatedBySupervisor(null);
        });
        supervisor.getTechniciansCreated().clear();

        userRepository.delete(supervisor);
        return SupervisorDtoMapper.toDto(supervisor);
    }

    //exclusive to superuser
    @Transactional
    @Override
    public SupervisorRetrievalDTO updateSupervisor(Integer id, UserInsertionDTO userInsertionDTO) {
        Supervisor supervisor = userRepository.findSupervisorById(id)
                        .orElseThrow(() -> new NotFoundException("Supervisor with id " + id + " not found"));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(userInsertionDTO, supervisor);
        if(userInsertionDTO.getPassword() != null) {
            supervisor.setPassword(passwordEncoder.encode(userInsertionDTO.getPassword()));
        }
        return SupervisorDtoMapper.toDto(
                userRepository.save(supervisor)
        );
    }


    //exclusive to supervisor or superuser
    @Transactional
    @Override
    public TechnicianRetrievalDTO updateTechnician(Integer id, TechnicianInsertionDTO technicianInsertionDTO) {
        Technician technician = userRepository.findTechnicianById(id)
                        .orElseThrow(() -> new NotFoundException("Technician with id " + id + " not found"));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(technicianInsertionDTO, technician);
        if(technicianInsertionDTO.getPassword() != null) {
            technician.setPassword(passwordEncoder.encode(technicianInsertionDTO.getPassword()));
        }
        return TechnicianDtoMapper.toDto(
                userRepository.save(technician)
        );
    }

    @Override
    public SupervisorSuperuserStatsDTO getSupervisorStats(Integer id) {
        userRepository.findSupervisorById(id)
                .orElseThrow(() -> new NotFoundException("Supervisor with id " + id + " not found"));
        return SupervisorSuperuserStatsDTO.builder()
                .completedInterventions(interventionRepository.findCompletedInterventions(null))
                .activeSites(siteRepository.findActiveSites())
                .availableUsers(userRepository.findAvailableUsers())
                .build();

    }

    @Override
    public TechnicianStatsDTO getTechnicianStats(Integer id) {
        userRepository.findTechnicianById(id)
                .orElseThrow(() -> new NotFoundException("Supervisor with id " + id + " not found"));
        return TechnicianStatsDTO.builder()
                .completedInterventions(interventionRepository.findCompletedInterventions(id))
                .activeSites(siteRepository.findActiveSites())
                .assignedInterventions(interventionRepository.findAssignedInterventions(id))
                .build();
    }

    @Override
    public List<TechnicianRetrievalDTO> getAllTechincians(int page, int size) {
        return userRepository.findAllTechnicians(PageRequest.of(page , size)).map(TechnicianDtoMapper::toDto).stream().toList();
    }

    @Override
    public List<UserRetrievalDTO> getUsersForIntervention() {
        return userRepository.findAll().stream().map(UserDtoMapper::toDto).toList();
    }

    @Override
    public UsersStatsDTO getUsersStats() {
            List<User> users = userRepository.findAll();
            Integer total = users.size();
            Integer active = (int) users.stream().filter(user -> user.getTechnicianStatus().equals(TechnicianStatus.AVAILABLE)).count();
            Integer technicians = (int) users.stream().filter(user -> user.getRole().equals("TECHNICIAN")).count();
            Integer admins = superUserRepository.findAll().size();
            Integer supervisors = (int) users.stream().filter(user -> user.getRole().equals("SUPERVISOR")).count();
            return UsersStatsDTO.builder()
                    .total(total)
                    .active(active)
                    .technicians(technicians)
                    .supervisors(supervisors)
                    .admins(admins)
                    .build();
    }

    @Override
    public Page<UserRetrievalDTO> findTechniciansSupervisors(String name, List<TechnicianStatus> statuses, List<String> roles, Pageable pageable) {
        List<Class<? extends User>> rolesAsClass = new ArrayList<>();

        if(roles != null) {
            if (roles.contains("Technician")) {
                rolesAsClass.add(Technician.class);
            }
            if (roles.contains("Supervisor")) {
                rolesAsClass.add(Supervisor.class);
            }
        }else
            rolesAsClass = null;

        return userRepository.findAllTechniciansSupervisors(name , statuses , rolesAsClass , pageable).map(UserDtoMapper::toDto);
    }

}
