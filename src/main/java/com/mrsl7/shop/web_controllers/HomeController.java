package com.mrsl7.shop.web_controllers;

import com.mrsl7.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("productsWithDiscount", productService.getAllWithDiscount());
        return "index";
    }
}
