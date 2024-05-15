package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.UserOrderDto;
import io.bookbar.bookbarbackend.entities.UserOrder;
import io.bookbar.bookbarbackend.service.UserOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userOrders")
@RequiredArgsConstructor
public class UserOrderController {
    private UserOrderService userOrderService;

    // Build Add User order REST API
    @PostMapping
    public ResponseEntity<UserOrderDto> createUserOrder(@RequestBody UserOrderDto userOrderDto){
        UserOrderDto savedUserOrder = userOrderService.createUserOrder(userOrderDto);
        return new ResponseEntity<>(savedUserOrder, HttpStatus.CREATED);
    }

    // Build Get User order REST API
    @GetMapping("{id}")
    public ResponseEntity<UserOrderDto> getUserOrderById(@PathVariable("id") Long id){
        UserOrderDto userOrderDto = userOrderService.getUserOrderById(id);
        return ResponseEntity.ok(userOrderDto);
    }

    // Build Get ALL User orders REST API
    @GetMapping
    public ResponseEntity<List<UserOrderDto>> getAllUserOrders(){
        List<UserOrderDto> userOrders = userOrderService.getAllUserOrders();
        return ResponseEntity.ok(userOrders);
    }

    // Build Update User Order REST API
    @PutMapping("{id}")
    public ResponseEntity<UserOrderDto> updatedUserOrder(@PathVariable("id") Long id,@RequestBody UserOrderDto updatedUserOrder){
        UserOrderDto userOrderDto = userOrderService.updateUserOrder(id, updatedUserOrder);
        return ResponseEntity.ok(userOrderDto);
    }

    // Build Delete User order REST API

    public ResponseEntity<String> deleteUserOrder(Long id){
        userOrderService.deleteUserOrder(id);
        return ResponseEntity.ok("User order deleted successfully!");
    }
}
