package com.mrsl7.shop.dto;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    String imageUrl;

    @NotNull
    Long categoryId;

    @Column(nullable = false)
    @Min(0)
    @Max(100)
    Integer discountPercent = 0;

    BigDecimal discountedPrice;
}
