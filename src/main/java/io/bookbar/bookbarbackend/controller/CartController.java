package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.CartDTO;
import io.bookbar.bookbarbackend.dto.CartItemDTO;
import io.bookbar.bookbarbackend.service.CartItemService;
import io.bookbar.bookbarbackend.service.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/{userId}")
    public ResponseEntity<CartDTO> createCart(@PathVariable Long userId) {
        try {
            CartDTO savedCart = cartService.createCart(userId);
            return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable("id") Long cartId) {
        try {
            CartDTO cartDto = cartService.getCartById(cartId);
            return ResponseEntity.ok(cartDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable Long userId) {
        try {
            CartDTO cartDto = cartService.getCartByUserId(userId);
            return ResponseEntity.ok(cartDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{cartId}/add-item/{bookId}")
    public ResponseEntity<CartDTO> addItemToCart(@PathVariable Long cartId, @PathVariable Long bookId) {
        try {
            CartDTO updatedCart = cartService.addItemToCart(cartId, bookId);
            return ResponseEntity.ok(updatedCart);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{cartId}/remove-item/{cartItemId}")
    public ResponseEntity<CartDTO> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long cartItemId) {
        try {
            CartDTO updatedCart = cartService.removeItemFromCart(cartId, cartItemId);
            return ResponseEntity.ok(updatedCart);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{cartId}/clear")
    public ResponseEntity<CartDTO> clearCart(@PathVariable Long cartId) {
        try {
            CartDTO clearedCart = cartService.clearCart(cartId);
            return ResponseEntity.ok(clearedCart);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable Long cartId, @RequestBody List<Long> bookIds) {
        try {
            CartDTO updatedCart = cartService.updateCart(cartId, bookIds);
            return ResponseEntity.ok(updatedCart);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        try {
            cartService.deleteCart(cartId);
            return ResponseEntity.ok("Cart deleted");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}