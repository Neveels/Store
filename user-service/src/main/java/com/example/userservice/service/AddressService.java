package com.example.userservice.service;

import com.example.userservice.dto.request.AddressRequest;
import com.example.userservice.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    AddressResponse addAddress(AddressRequest address);

    List<AddressResponse> getAllAddresses();

    AddressResponse editAddress(AddressRequest address);

    void deleteById(Integer id);

    List<AddressResponse> changeActiveAddress(Integer id);
}

