package com.mrsl7.shop.mapper;

import com.mrsl7.shop.dto.ProductDto;
import com.mrsl7.shop.entity.Category;
import com.mrsl7.shop.entity.Product;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toProductDto(Product product); //map Product to ProductDto
    List<ProductDto> toProductDtoList(List<Product> products); //map list of Product to list of ProductDto
    @Mapping(source = "categoryId", target = "category")
    Product toProduct(ProductDto dto);
    List<Product> toProductList(List<ProductDto> productsDto);

    default Category mapCategory(Long categoryId) {
        if (categoryId == null) return null;
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }

    @AfterMapping
    default void calcDiscount(@MappingTarget ProductDto dto, Product entity) {
        if(entity.getDiscountPercent() == 0) {
            dto.setDiscountedPrice(dto.getPrice());
            return;
        }
        int d = entity.getDiscountPercent();
        BigDecimal discounted = entity.getPrice()
                .multiply(BigDecimal.valueOf(100 - d))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        dto.setDiscountedPrice(discounted);
    }
}
