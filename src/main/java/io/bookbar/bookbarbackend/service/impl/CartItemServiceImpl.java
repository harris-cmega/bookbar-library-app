package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.CartItemDTO;
import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.entities.Cart;
import io.bookbar.bookbarbackend.entities.CartItem;
import io.bookbar.bookbarbackend.mapper.CartItemMapper;
import io.bookbar.bookbarbackend.repository.BookRepository;
import io.bookbar.bookbarbackend.repository.CartItemRepository;
import io.bookbar.bookbarbackend.repository.CartRepository;
import io.bookbar.bookbarbackend.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public CartItemDTO addCartItem(Long cartId, Long bookId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setBook(book);
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        return CartItemMapper.toCartItemDTO(savedCartItem);
    }

    @Override
    public CartItemDTO updateCartItem(Long cartItemId, Long bookId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("CartItem not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        cartItem.setBook(book);
        CartItem updatedCartItem = cartItemRepository.save(cartItem);
        return CartItemMapper.toCartItemDTO(updatedCartItem);
    }

    @Override
    public void removeCartItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public List<CartItemDTO> getCartItemsByCartId(Long cartId) {
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        return cartItems.stream().map(CartItemMapper::toCartItemDTO).collect(Collectors.toList());
    }

    @Override
    public CartItemDTO getCartItemById(Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() -> new RuntimeException("CartItem not found"));
        return CartItemMapper.toCartItemDTO(cartItem);
    }
}
