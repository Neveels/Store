package com.example.productservice.repository;

import com.example.productservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Neevels
 * @version 1.0
 * @date 4/10/2023 9:19 PM
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, Integer> {

}
