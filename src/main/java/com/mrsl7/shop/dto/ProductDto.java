package com.mrsl7.shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    Long id;

    @NotNull
    @Size(min = 1, max = 100)
    String name;

    @NotNull
    BigDecimal price;

    @NotNull
    String description;

    @NotNull
    Integer quantity;

    @NotNull
    String imageUrl;

    @NotNull
    Long categoryId;
}
