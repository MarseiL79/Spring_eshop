package com.mrsl7.shop.mapper;

import com.mrsl7.shop.dto.CategoryDto;
import com.mrsl7.shop.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    CategoryDto toCategoryDto(Category category);

    Category toProduct(CategoryDto dto);
}
