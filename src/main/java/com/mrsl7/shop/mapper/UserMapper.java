package com.mrsl7.shop.mapper;

import com.mrsl7.shop.dto.UserDto;
import com.mrsl7.shop.entity.Role;
import com.mrsl7.shop.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(source = "roles", target = "roles")
    UserDto toUserDto(User user);
    @Mapping(target = "roles", ignore = true) // назначим роли вручную в сервисе
    User toUser(UserDto dto);

    default Set<String> mapRoles(Set<Role> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(Role::getName)
                .collect(Collectors.toSet());
    }
}
