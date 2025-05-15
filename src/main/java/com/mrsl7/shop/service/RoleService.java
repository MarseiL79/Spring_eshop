package com.mrsl7.shop.service;

import com.mrsl7.shop.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto findByName(String name);
    List<RoleDto> getAll();
}
