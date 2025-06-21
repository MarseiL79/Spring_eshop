package com.mrsl7.shop.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    private String description;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotNull
    private Integer quantity;

    @Column(nullable = true)
    private String imageUrl;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(nullable = false)
    @Min(0)
    @Max(100)
    Integer discountPercent = 0;
}
