package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.CartDTO;
import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.entities.Cart;
import io.bookbar.bookbarbackend.entities.CartItem;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.CartMapper;
import io.bookbar.bookbarbackend.repository.BookRepository;
import io.bookbar.bookbarbackend.repository.CartItemRepository;
import io.bookbar.bookbarbackend.repository.CartRepository;
import io.bookbar.bookbarbackend.repository.UserRepository;
import io.bookbar.bookbarbackend.service.CartService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public CartDTO createCart(Long userId) {
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> {
                        log.error("User not found with ID: {}", userId);
                        return new ResourceNotFoundException("User not found");
                    });

            Cart cart = new Cart();
            cart.setUser(user);

            Cart savedCart = cartRepository.save(cart);

            return CartMapper.toCartDTO(savedCart);
        } catch (Exception e) {
            log.error("Error creating cart for user with ID: {}", userId, e);
            throw e;
        }
    }


    @Override
    public CartDTO getCartById(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        return CartMapper.toCartDTO(cart);
    }

    @Override
    public CartDTO getCartByUserId(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            throw new ResourceNotFoundException("Cart not found for user");
        }
        return CartMapper.toCartDTO(cart);
    }

    @Override
    public CartDTO addItemToCart(Long cartId, Long bookId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (cart.getCartItems().stream().anyMatch(item -> item.getBook().getId().equals(bookId))) {
            throw new IllegalArgumentException("Book already exists in the cart");
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setBook(book);
        cartItemRepository.save(cartItem);

        cart.getCartItems().add(cartItem);
        Cart updatedCart = cartRepository.save(cart);
        return CartMapper.toCartDTO(updatedCart);
    }

    @Override
    public CartDTO removeItemFromCart(Long cartId, Long cartItemId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("CartItem not found"));

        cart.getCartItems().remove(cartItem);
        cartItemRepository.delete(cartItem);

        Cart updatedCart = cartRepository.save(cart);
        return CartMapper.toCartDTO(updatedCart);
    }

    @Override
    public CartDTO clearCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        cartItemRepository.deleteAll(cart.getCartItems());
        cart.getCartItems().clear();

        Cart updatedCart = cartRepository.save(cart);
        return CartMapper.toCartDTO(updatedCart);
    }

    @Override
    public CartDTO updateCart(Long cartId, List<Long> bookIds) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        List<Book> books = bookRepository.findAllById(bookIds);
        if (books.size() != bookIds.size()) {
            throw new ResourceNotFoundException("One or more books not found");
        }

        cartItemRepository.deleteAll(cart.getCartItems());
        List<CartItem> cartItems = books.stream()
                .map(book -> {
                    CartItem cartItem = new CartItem();
                    cartItem.setCart(cart);
                    cartItem.setBook(book);
                    return cartItem;
                })
                .collect(Collectors.toList());

        cart.setCartItems(cartItems);
        Cart updatedCart = cartRepository.save(cart);
        return CartMapper.toCartDTO(updatedCart);
    }

    @Override
    public void deleteCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        cartRepository.delete(cart);
    }
}
