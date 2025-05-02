package com.mrsl7.shop.service;

import com.mrsl7.shop.dto.CategoryDto;
import com.mrsl7.shop.entity.Category;
import com.mrsl7.shop.mapper.CategoryMapper;
import com.mrsl7.shop.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repo;
    private final CategoryMapper mapper;

    @Override
    public List<CategoryDto> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getById(Long id) {
        Category entity = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        return mapper.toCategoryDto(entity);
    }

    @Override
    public CategoryDto create(CategoryDto dto) {
        Category entity = mapper.toCategory(dto);
        Category saved = repo.save(entity);
        return mapper.toCategoryDto(saved);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto dto) {
        Category existing = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        // Обновляем только нужные поля
        existing.setName(dto.getName());

        Category updated = repo.save(existing);
        return mapper.toCategoryDto(updated);
    }

    @Override
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Category not found");
        }
        repo.deleteById(id);
    }
}
