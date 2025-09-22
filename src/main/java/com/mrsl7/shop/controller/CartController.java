package com.mrsl7.shop.controller;

import com.mrsl7.shop.dto.CartItem;
import com.mrsl7.shop.service.CartService;
import com.mrsl7.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final ProductService productService;
    private final CartService cartService;

    /**
     * Принимает JSON { "productId": 1, "quantity": 2 }
     */
    @PostMapping("/add")
    public ResponseEntity<?> addToCartJson(@RequestBody CartItem dto) {
        var product = productService.getById(dto.getProductId());
        cartService.add(product, dto.getQuantity() == 0 ? 1 : dto.getQuantity());
        return ResponseEntity.ok(Map.of("status", "ok"));
    }

    /**
     * Альтернативный вариант: принимает form data (productId, quantity)
     */
    @PostMapping(path = "/add", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<?> addToCartForm(@RequestParam Long productId,
                                           @RequestParam(defaultValue = "1") Integer quantity) {
        var product = productService.getById(productId);
        cartService.add(product, quantity);
        return ResponseEntity.ok(Map.of("status", "ok"));
    }
}
