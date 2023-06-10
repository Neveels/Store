package com.example.userservice.repository;

import com.example.userservice.entity.Favorite;
import com.example.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    List<Favorite> findAllByUserId(Long id);
    void deleteByIdAndUser(Integer id, User user);
    void deleteAllByProductId(String id);
}
