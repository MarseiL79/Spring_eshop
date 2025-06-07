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
public class RegisterRequest {
    @NotNull
    @Size(min = 1, max = 100)
    private String username;

    @NotNull
    @Size(min = 1, max = 100)
    String rawPassword;
}
