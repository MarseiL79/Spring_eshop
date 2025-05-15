package com.mrsl7.shop.service;

import com.mrsl7.shop.dto.UserDto;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserDto register(String username, String rawPassword, Set<String> roleNames);
    UserDto findByUsername(String username);
    List<UserDto> getAll();
}
