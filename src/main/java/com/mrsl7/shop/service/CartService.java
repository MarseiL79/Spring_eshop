package com.mrsl7.shop.service;

import com.mrsl7.shop.dto.CartItem;
import com.mrsl7.shop.dto.ProductDto;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {
    private final Map<Long, CartItem> items = new LinkedHashMap<>();

    public void add(ProductDto p, int qty) {
        items.compute(p.getId(), (id, old) -> {
            if (old == null) {
                return new CartItem(p.getId(), p.getName(), p.getDiscountedPrice(), qty);
            } else {
                old.setQuantity(old.getQuantity() + qty);
                return old;
            }
        });
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public BigDecimal getTotal() {
        return items.values().stream()
                .map(CartItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clear() {
        items.clear();
    }
}
