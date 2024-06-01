package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.CartItemDTO;
import io.bookbar.bookbarbackend.service.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/{cartId}/add/{bookId}")
    public ResponseEntity<CartItemDTO> addCartItem(@PathVariable Long cartId, @PathVariable Long bookId) {
        try {
            CartItemDTO savedCartItem = cartItemService.addCartItem(cartId, bookId);
            return new ResponseEntity<>(savedCartItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{cartItemId}/update/{bookId}")
    public ResponseEntity<CartItemDTO> updateCartItem(@PathVariable Long cartItemId, @PathVariable Long bookId) {
        try {
            CartItemDTO updatedCartItem = cartItemService.updateCartItem(cartItemId, bookId);
            return ResponseEntity.ok(updatedCartItem);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{cartItemId}/remove")
    public ResponseEntity<String> removeCartItem(@PathVariable Long cartItemId) {
        try {
            cartItemService.removeCartItem(cartItemId);
            return ResponseEntity.ok("CartItem deleted");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartItemDTO>> getCartItemsByCartId(@PathVariable Long cartId) {
        try {
            List<CartItemDTO> cartItems = cartItemService.getCartItemsByCartId(cartId);
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{cartItemId}/item")
    public ResponseEntity<CartItemDTO> getCartItemById(@PathVariable Long cartItemId) {
        try {
            CartItemDTO cartItem = cartItemService.getCartItemById(cartItemId);
            return ResponseEntity.ok(cartItem);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
