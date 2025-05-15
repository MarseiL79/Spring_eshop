package com.mrsl7.shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotNull
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String username;

    @NotNull
    private Set<String> roles; // просто список имён ролей
}
