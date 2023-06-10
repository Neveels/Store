package com.example.userservice.dto.mapper;

import com.example.userservice.dto.request.AddressRequest;
import com.example.userservice.dto.response.AddressResponse;
import com.example.userservice.entity.Address;
import com.example.userservice.entity.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class AddressMapper {
    public AddressResponse toResponseDto(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .status(address.getStatus())
                .state(address.getState())
                .apartment(address.getApartment())
                .city(address.getCity())
                .country(address.getCountry())
                .street(address.getStreet())
                .house(address.getHouse())
                .build();
    }

    public Address updateAddress(AddressRequest address, Integer id, boolean status, User user) {
        return Address.builder()
                .id(id)
                .house(address.getHouse())
                .state(address.getState())
                .status(status)
                .street(address.getStreet())
                .apartment(address.getApartment())
                .city(address.getCity())
                .country(address.getCountry())
                .user(user)
                .build();
    }

}
