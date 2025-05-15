package com.mrsl7.shop.service;

import com.mrsl7.shop.dto.RoleDto;
import com.mrsl7.shop.entity.Role;
import com.mrsl7.shop.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.mrsl7.shop.mapper.RoleMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repo;
    private final RoleMapper mapper;

    @Override
    public RoleDto findByName(String name) {
        Role role = repo.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Role " + name + " not found"));
        return mapper.toRoleDto(role);
    }

    @Override
    public List<RoleDto> getAll() {
        return repo.findAll().stream().map(mapper::toRoleDto).toList();
    }
}
