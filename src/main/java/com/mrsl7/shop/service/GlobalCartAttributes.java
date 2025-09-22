package com.mrsl7.shop.service;

import com.mrsl7.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalCartAttributes {

    private final CartService cartService;

    @ModelAttribute("cartItemCount")
    public int cartItemCount() {
        return cartService.getItemCount();
    }
}