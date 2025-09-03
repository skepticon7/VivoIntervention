package org.example.backend.Mapper.User;

import org.example.backend.DTO.User.Retrieval.UserRetrievalDTO;
import org.example.backend.Entities.User;
import org.example.backend.Enums.InterventionStatus;

public class UserDtoMapper {
    public static UserRetrievalDTO toDto(User user) {
        if(user == null) return null;
        return UserRetrievalDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .hireDate(user.getHireDate())
                .createdBy(user.getCreatedBySuperuser() != null ? user.getCreatedBySuperuser().getId() : user.getCreatedBySupervisor().getId())
                .technicianStatus(user.getTechnicianStatus().toString())
                .interventionsCompleted((int) user.getInterventionsAssigned().stream().filter(inter -> inter.getInterventionStatus().equals(InterventionStatus.COMPLETED)).count())
                .interventionsAssigned(user.getInterventionsAssigned() != null ? user.getInterventionsAssigned().stream().map(intervention -> intervention.getId()).toList() : null)
                .interventionsCreated(user.getInterventionsCreated() != null ? user.getInterventionsCreated().stream().map(intervention -> intervention.getId()).toList() : null)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
