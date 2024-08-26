package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.UserOrderItemsDTO;
import io.bookbar.bookbarbackend.service.UserOrderItemsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/order-items")
public class UserOrderItemsController {

    private final UserOrderItemsService userOrderItemsService;

    @PostMapping("/{orderId}/add/{bookId}")
    public ResponseEntity<UserOrderItemsDTO> addUserOrderItem(@PathVariable Long orderId, @PathVariable Long bookId) {
        try {
            UserOrderItemsDTO savedOrderItem = userOrderItemsService.addUserOrderItem(orderId, bookId);
            return new ResponseEntity<>(savedOrderItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{orderItemId}/update/{bookId}")
    public ResponseEntity<UserOrderItemsDTO> updateUserOrderItem(@PathVariable Long orderItemId, @PathVariable Long bookId) {
        try {
            UserOrderItemsDTO updatedOrderItem = userOrderItemsService.updateUserOrderItem(orderItemId, bookId);
            return ResponseEntity.ok(updatedOrderItem);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{orderItemId}/remove")
    public ResponseEntity<String> removeUserOrderItem(@PathVariable Long orderItemId) {
        try {
            userOrderItemsService.removeUserOrderItem(orderItemId);
            return ResponseEntity.ok("Order item deleted");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<UserOrderItemsDTO>> getUserOrderItemsByOrderId(@PathVariable Long orderId) {
        try {
            List<UserOrderItemsDTO> orderItems = userOrderItemsService.getUserOrderItemsByOrderId(orderId);
            return ResponseEntity.ok(orderItems);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{orderItemId}/item")
    public ResponseEntity<UserOrderItemsDTO> getUserOrderItemById(@PathVariable Long orderItemId) {
        try {
            UserOrderItemsDTO orderItem = userOrderItemsService.getUserOrderItemById(orderItemId);
            return ResponseEntity.ok(orderItem);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
