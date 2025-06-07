package com.mrsl7.shop.service;

import com.mrsl7.shop.dto.ProductDto;
import com.mrsl7.shop.dto.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto findByName(String name);
    List<RoleDto> getAll();
    RoleDto getById(Long id);
    RoleDto create(RoleDto dto);
    RoleDto update(Long id, RoleDto dto);
    void delete(Long id);
}
