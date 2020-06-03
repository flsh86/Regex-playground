package com.example.mappers;

import com.example.address.Address;
import com.example.address.AddressDTO;

public class AddressMapper {
    public static Address toEntity(AddressDTO dto) {
        return new Address(
                dto.getId(),
                dto.getStreet(),
                dto.getCity(),
                dto.getZipCode()
        );
    }

    public static AddressDTO toDTO(Address entity) {
        return new AddressDTO(
                entity.getId(),
                entity.getStreet(),
                entity.getCity(),
                entity.getZipCode()
        );
    }
}
