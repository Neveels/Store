package com.example.userservice.repository;

import com.example.userservice.entity.mongo.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    List<Cart> findAllByUserId(Long id);
}
