package com.mrsl7.shop.service;

import com.mrsl7.shop.dto.UserDto;
import com.mrsl7.shop.entity.Role;
import com.mrsl7.shop.entity.User;
import com.mrsl7.shop.mapper.UserMapper;
import com.mrsl7.shop.repository.RoleRepository;
import com.mrsl7.shop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final UserMapper mapper;

    @Override
    public UserDto register(String username, String rawPassword) {
        if (userRepo.existsByUsername(username)) {
            throw new IllegalArgumentException("Username " + username + " already in use");
        }
        // Найти сущность роли USER
        Role userRole = roleRepo.findByName("USER")
                .orElseThrow(() -> new EntityNotFoundException("Default role USER not found"));

        // Создать пользователя
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(rawPassword);
        user.setRoles(Set.of(userRole));

        User saved = userRepo.save(user);

        return mapper.toUserDto(saved);
    }
    @Override
    public UserDto findByUsername(String username) {
        User user = userRepo.findByUsername(username).
                orElseThrow(() -> new EntityNotFoundException("User not found: " + username));

        return mapper.toUserDto(user);
    }

    @Override
    public List<UserDto> getAll() {
        return userRepo.findAll().stream().map(mapper::toUserDto).toList();
    }

    @Override
    public UserDto getById(Long id) {
        User user = userRepo.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        return mapper.toUserDto(user);
    }

    @Override
    public void assignRoles(Long id, Set<String> roleNames) {
        User user = userRepo.findById(id).
                orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));

        Set<Role> roles = roleNames.stream()
                .map(name -> roleRepo.findByName(name)
                        .orElseThrow(() -> new EntityNotFoundException("Role not found: " + name)))
                .collect(Collectors.toSet());

        user.setRoles(roles);
        userRepo.save(user);
    }
}
