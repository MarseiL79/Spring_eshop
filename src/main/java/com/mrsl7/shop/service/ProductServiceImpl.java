package com.mrsl7.shop.service;

import com.mrsl7.shop.dto.ProductDto;
import com.mrsl7.shop.entity.Category;
import com.mrsl7.shop.entity.Product;
import com.mrsl7.shop.mapper.ProductMapper;
import com.mrsl7.shop.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;
    private final ProductMapper mapper;

    @Override
    public List<ProductDto> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getById(Long id) {
        Product entity = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return mapper.toProductDto(entity);
    }

    @Override
    public ProductDto create(ProductDto dto) {
        Product entity = mapper.toProduct(dto);
        Product saved = repo.save(entity);
        return mapper.toProductDto(saved);
    }

    @Override
    public ProductDto update(Long id, ProductDto dto) {
        Product existing = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        // Обновляем только нужные поля
        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setQuantity(dto.getQuantity());
        // Если есть категория в DTO — устанавливаем
        if (dto.getCategoryId() != null) {
            Category category = new Category();
            category.setId(dto.getCategoryId());
            existing.setCategory(category);
        }

        Product updated = repo.save(existing);
        return mapper.toProductDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Product not found");
        }
        repo.deleteById(id);
    }
}
