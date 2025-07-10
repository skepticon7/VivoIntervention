package org.example.backend.Mapper.User;

import org.example.backend.DTO.User.Insertion.SuperUserInsertionDTO;
import org.example.backend.DTO.User.Retrieval.SuperUserRetrievalDTO;
import org.example.backend.Entities.SuperUser;

public class SuperUserDtoMapper {
    public static SuperUserRetrievalDTO toDto(SuperUser superUser) {
        if(superUser == null) return null;
        return SuperUserRetrievalDTO.builder()
                .fullName(superUser.getFullName())
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
                .fullName(superUserInsertionDTO.getFullName())
                .email(superUserInsertionDTO.getEmail())
                .phoneNumber(superUserInsertionDTO.getPhoneNumber())
                .userCreatedBy(null)
                .build();
    }
}
