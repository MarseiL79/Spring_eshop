package com.mrsl7.shop.service;

import com.mrsl7.shop.dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    ProductDto getById(Long id);
    ProductDto create(ProductDto dto);
    ProductDto update(Long id, ProductDto dto);
    void delete(Long id);
    List<ProductDto> getByCategory(Long categoryId);
}
