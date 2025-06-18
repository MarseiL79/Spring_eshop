package com.mrsl7.shop.web_controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mrsl7.shop.service.CategoryService;
import com.mrsl7.shop.service.ProductService;

@Controller
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryWebController {

    private final CategoryService categoryService;
    private final ProductService productService;

    // 1) список всех категорий
    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "categories";
    }

    // 2) список товаров в выбранной категории
    @GetMapping("/{id}/products")
    public String listByCategory(@PathVariable Long id, Model model) {
        model.addAttribute("products", productService.getByCategory(id));
        // ну может ещё имя категории
        model.addAttribute("categoryName", categoryService.getById(id).getName());
        return "products";
    }
}
