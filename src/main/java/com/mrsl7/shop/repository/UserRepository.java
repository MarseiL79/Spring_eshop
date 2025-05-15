package com.mrsl7.shop.repository;

import com.mrsl7.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String username);
    boolean existsByUsername(String username);
}
