package org.example.backend.ServiceImplementation;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.example.backend.DTO.User.Insertion.TechnicianInsertionDTO;
import org.example.backend.DTO.User.Insertion.UserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.SupervisorRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.TechnicianRetrievalDTO;
import org.example.backend.DTO.User.Retrieval.UserRetrievalDTO;
import org.example.backend.Entities.*;
import org.example.backend.Exceptions.AlreadyExistsException;
import org.example.backend.Exceptions.NotFoundException;
import org.example.backend.Mapper.User.SuperUserDtoMapper;
import org.example.backend.Mapper.User.SupervisorDtoMapper;
import org.example.backend.Mapper.User.TechnicianDtoMapper;
import org.example.backend.Repository.InterventionTypeRepository;
import org.example.backend.Repository.SiteRepository;
import org.example.backend.Repository.SuperUserRepository;
import org.example.backend.Repository.UserRepository;
import org.example.backend.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final SiteRepository siteRepository;
    private final InterventionTypeRepository interventionTypeRepository;
    private final SuperUserRepository superUserRepository;
    private final ModelMapper modelMapper;


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
        SuperUser superUser = superUserRepository.findById(createdById)
                    .orElseThrow(() -> new NotFoundException("SuperUser with id " + createdById + " not found"));
        supervisor.setCreatedBySuperuser(superUser);
        superUser.getUsers().add(supervisor);

        // affect all sites / interventionTypes / technicians
        List<Site> sites = siteRepository.findAll();
        List<InterventionType> interventionTypes = interventionTypeRepository.findAll();
        List<Technician> technicians = userRepository.findAllTechnicians();

//        sites.forEach(site -> site.getSupervisors_technicians().add(supervisor));
//        interventionTypes.forEach(interventionType -> interventionType.getSupervisors_technicians().add(supervisor));
//        technicians.forEach(technician -> technician.getSupervisors().add(supervisor));

        supervisor.setSites(sites);
        supervisor.setInterventionTypes(interventionTypes);
        supervisor.setTechnicians(technicians);


        return SupervisorDtoMapper.toDto(
                userRepository.save(supervisor)
        );
    }

    @Transactional
    @Override
    public TechnicianRetrievalDTO createTechnician(TechnicianInsertionDTO technicianInsertionDTO) {

        Optional<User> userCheck = userRepository.findUserByEmail(technicianInsertionDTO.getEmail());

        if(userCheck.isPresent())
            throw new AlreadyExistsException("User with email " + technicianInsertionDTO.getEmail() + " already exists");

        Technician technician = TechnicianDtoMapper.toEntity(technicianInsertionDTO);
        Integer createdById = technicianInsertionDTO.getCreatedBy();
        List<Site> sites = siteRepository.findAll();
        List<InterventionType> interventionTypes = interventionTypeRepository.findAll();
        List<Supervisor> supervisors = userRepository.findAllSupervisors();

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

        technician.setSites(sites);
        technician.setInterventionTypes(interventionTypes);
        technician.setSupervisors(supervisors);

        supervisors.forEach(supervisor -> {
            supervisor.getTechnicians().add(technician);
        });

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

        // Remove technician from all related entities
        technician.getSites().clear();
        technician.getInterventionTypes().clear();
        technician.getSupervisors().clear();

        List<Supervisor> supervisors = userRepository.findAllSupervisors();
        supervisors.forEach(supervisor -> {
            supervisor.getTechnicians().remove(technician);
        });

        if(technician.getCreatedBySupervisor() != null) {
            technician.getCreatedBySupervisor().getTechnicians().remove(technician);
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
                .orElseThrow(() -> new NotFoundException("Technician with id " + id + " not found"));

        // Remove technician from all related entities
        supervisor.getSites().clear();
        supervisor.getInterventionTypes().clear();
        supervisor.getTechnicians().clear();
        supervisor.getTechniciansCreated().forEach(technician -> {
            technician.setCreatedBySupervisor(null);
        });
        supervisor.getTechniciansCreated().clear();

        userRepository.delete(supervisor);
        return SupervisorDtoMapper.toDto(supervisor);
    }

    //exclusive to superuser
    @Override
    public SupervisorRetrievalDTO updateSupervisor(Integer id, UserInsertionDTO userInsertionDTO) {
        Supervisor supervisor = userRepository.findSupervisorById(id)
                        .orElseThrow(() -> new NotFoundException("Supervisor with id " + id + " not found"));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(userInsertionDTO, supervisor);
        return SupervisorDtoMapper.toDto(
                userRepository.save(supervisor)
        );
    }


    //exclusive to supervisor or superuser
    @Override
    public TechnicianRetrievalDTO updateTechnician(Integer id, TechnicianInsertionDTO technicianInsertionDTO) {
        Technician technician = userRepository.findTechnicianById(id)
                        .orElseThrow(() -> new NotFoundException("Technician with id " + id + " not found"));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(technicianInsertionDTO, technician);
        return TechnicianDtoMapper.toDto(
                userRepository.save(technician)
        );
    }

}
