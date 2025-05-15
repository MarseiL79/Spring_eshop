package com.mrsl7.shop.service;

import com.mrsl7.shop.dto.UserDto;
import com.mrsl7.shop.mapper.UserMapper;
import com.mrsl7.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final UserMapper mapper;

    @Override
    public UserDto register(String username, String rawPassword, Set<String> roleNames) {
        return null;
    }

    @Override
    public UserDto findByUsername(String username) {
        return null;
    }

    @Override
    public List<UserDto> getAll() {
        return repo.findAll().stream().map(mapper::toUserDto).toList();
    }
}
