package com.mrsl7.shop.web_controllers;

import com.mrsl7.shop.service.CartService;
import com.mrsl7.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartWebController {

    private final ProductService productService;
    private final CartService cartService;
    @GetMapping
    public String cart(Model model) {
        model.addAttribute("empty", cartService.isEmpty());
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, @RequestParam(defaultValue="1") int qty) {
        var prod = productService.getById(id);
        cartService.add(prod, qty);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/cart";
    }
}
