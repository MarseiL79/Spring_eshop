package com.mrsl7.shop.service;

import com.mrsl7.shop.dto.RoleDto;
import com.mrsl7.shop.entity.Role;
import com.mrsl7.shop.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    @Override
    public RoleDto getById(Long id) {
        Role role = repo.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " not found."));
        return mapper.toRoleDto(role);
    }

    @Override
    public RoleDto create(RoleDto dto) {
        Role role = mapper.toRole(dto);
        Role saved = repo.save(role);
        return mapper.toRoleDto(saved);
    }

    @Override
    public RoleDto update(Long id, RoleDto dto) {
        Role existing = repo.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " not found."));

        existing.setName(dto.getName());
        Role role = repo.save(existing);

        return mapper.toRoleDto(role);
    }

    @Override
    public void delete(Long id) {
        Role existing = repo.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Role with id " + id + " not found."));

        repo.delete(existing);
    }
}
