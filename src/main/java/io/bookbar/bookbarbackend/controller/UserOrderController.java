package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.UserOrderDTO;
import io.bookbar.bookbarbackend.service.UserOrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user-orders")
public class UserOrderController {

    private final UserOrderService userOrderService;

    @PostMapping
    public ResponseEntity<UserOrderDTO> createUserOrder(@RequestBody @Valid UserOrderDTO userOrderDTO) {
        UserOrderDTO savedUserOrder = userOrderService.createUserOrder(userOrderDTO);
        return new ResponseEntity<>(savedUserOrder, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserOrderDTO> getUserOrderById(@PathVariable("id") Long userOrderId) {
        UserOrderDTO userOrderDTO = userOrderService.getUserOrderById(userOrderId);
        return ResponseEntity.ok(userOrderDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserOrderDTO>> getAllUserOrders() {
        List<UserOrderDTO> userOrders = userOrderService.getAllUserOrders();
        return ResponseEntity.ok(userOrders);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserOrderDTO> updateUserOrder(@PathVariable("id") Long userOrderId, @RequestBody @Valid UserOrderDTO userOrderDTO) {
        UserOrderDTO updatedUserOrder = userOrderService.updateUserOrder(userOrderId, userOrderDTO);
        return ResponseEntity.ok(updatedUserOrder);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserOrder(@PathVariable("id") Long userOrderId) {
        userOrderService.deleteUserOrder(userOrderId);
        return ResponseEntity.ok("User order deleted");
    }
}
