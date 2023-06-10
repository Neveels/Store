package com.example.userservice.repository;

import com.example.userservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findAllByUserId(Long id);
    List<Address> findAllByUserIdAndStatusEquals(Long id, boolean status);
    Optional<Address> findAddressByUserIdAndAndId(Long userId, Integer addressId);

}

