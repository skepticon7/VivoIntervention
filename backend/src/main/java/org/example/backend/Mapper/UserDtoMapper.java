package org.example.backend.Mapper;

import org.example.backend.DTO.UserInsertionDTO;
import org.example.backend.DTO.UserRetrievalDTO;
import org.example.backend.Entities.SuperUser;
import org.example.backend.Entities.Supervisor;
import org.example.backend.Entities.Technician;
import org.example.backend.Entities.User;

public class UserDtoMapper {
    public static UserRetrievalDTO toDto(User user){
        if(user == null) return null;
        return UserRetrievalDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .phoneNumber(user.getPhoneNumber())
                .birthDate(user.getBirthDate())
                .build();
    }

    public static User toEntity(UserInsertionDTO userInsertionDTO){
        if(userInsertionDTO == null) return null;
        return  SuperUser.builder()
                .firstName(userInsertionDTO.getFirstName())
                .lastName(userInsertionDTO.getLastName())
                .email(userInsertionDTO.getEmail())
                .phoneNumber(userInsertionDTO.getPhoneNumber())
                .birthDate(userInsertionDTO.getBirthDate())
                .password(userInsertionDTO.getPassword())
                .build();
    }



}
