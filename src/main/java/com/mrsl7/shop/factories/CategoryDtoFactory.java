package com.mrsl7.shop.factories;

import com.mrsl7.shop.dto.CategoryDto;
import com.mrsl7.shop.dto.ProductDto;
import com.mrsl7.shop.entity.Category;
import com.mrsl7.shop.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoFactory {

    public CategoryDto makeCategoryDto(Category entity) {
        return CategoryDto.builder().id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public ProductDto makeProductDto(Product entity) {
        return ProductDto.builder().id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .description(entity.getDescription())
                .quantity(entity.getQuantity())
                .categoryId(entity.getCategory().getId())
                .build();
    }
}
