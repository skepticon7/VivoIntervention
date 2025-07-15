package org.example.backend.Mapper.User;

import org.example.backend.DTO.User.Insertion.SuperUserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.SuperUserRetrievalDTO;
import org.example.backend.Entities.SuperUser;

public class SuperUserDtoMapper {
    public static SuperUserRetrievalDTO toDto(SuperUser superUser) {
        if(superUser == null) return null;
        return SuperUserRetrievalDTO.builder()
                .fullName(superUser.getFirstName().concat(" ").concat(superUser.getLastName()))
                .id(superUser.getId())
                .phoneNumber(superUser.getPhoneNumber())
                .role(superUser.getRole())
                .email(superUser.getEmail())
                .createdAt(superUser.getCreatedAt())
                .updatedAt(superUser.getUpdatedAt())
                .build();
    }

    public static SuperUser toEntity(SuperUserInsertionDTO superUserInsertionDTO){
        if(superUserInsertionDTO == null) return null;
        return SuperUser.builder()
                .firstName(superUserInsertionDTO.getFirstName())
                .lastName(superUserInsertionDTO.getLastName())
                .email(superUserInsertionDTO.getEmail())
                .phoneNumber(superUserInsertionDTO.getPhoneNumber())
                .password(superUserInsertionDTO.getPassword())
                .userCreatedBy(null)
                .build();
    }
}
