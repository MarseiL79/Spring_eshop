package com.mrsl7.shop.mapper;

import com.mrsl7.shop.dto.RoleDto;
import com.mrsl7.shop.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    RoleDto toRoleDto(Role role);
    Role toRole(RoleDto dto);
}
