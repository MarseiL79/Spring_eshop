package com.mrsl7.shop.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @NonNull
    Long id;

    @NonNull
    String name;

    @NonNull
    BigDecimal price;

    @NonNull
    String description;

    @NonNull
    Integer quantity;

    @NonNull
    Long categoryId;
}
