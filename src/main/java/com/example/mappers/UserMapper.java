package com.example.mappers;

import com.example.user.User;
import com.example.user.UserDTO;

public class UserMapper {
    public static User toEntity(UserDTO dto) {
        return new User(
                dto.getId(),
                dto.getName(),
                dto.getLastName(),
                dto.getEmail(),
                dto.getPhoneNumber()
        );
    }

    public static UserDTO toDTO(User entity) {
        String address = entity.getAddress().toString();
        return new UserDTO(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                address
        );
    }
}
