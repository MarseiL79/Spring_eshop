package com.mrsl7.shop.web_controllers;

import com.mrsl7.shop.dto.CategoryDto;
import com.mrsl7.shop.dto.ProductDto;
import com.mrsl7.shop.service.CategoryService;
import com.mrsl7.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ProductWebController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/product/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        ProductDto productDto = productService.getById(id);
        CategoryDto categoryDto = categoryService.getById(productDto.getCategoryId());
        model.addAttribute("product", productDto);
        model.addAttribute("category", categoryDto);
        return "product";
    }
}
