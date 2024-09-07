package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.UserOrderDTO;
import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.service.UserOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user-orders")
public class UserOrderController {

    private final UserOrderService userOrderService;

    @PostMapping("/{userId}")
    public ResponseEntity<UserOrderDTO> createOrder(@PathVariable Long userId,
                                                    @RequestParam BigDecimal totalPrice) {
        try {
            UserOrderDTO createdOrder = userOrderService.createOrder(userId, totalPrice);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserOrderDTO>> getAllOrders() {
        List<UserOrderDTO> orders = userOrderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

   /* @GetMapping("/{id}")
    public ResponseEntity<UserOrderDTO> getOrderById(@PathVariable("id") Long orderId) {
        try {
            UserOrderDTO orderDto = userOrderService.getOrderById(orderId);
            return ResponseEntity.ok(orderDto);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
*/
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserOrderDTO>> getOrdersByUserId(@PathVariable Long userId) {
        try {
            List<UserOrderDTO> orders = userOrderService.getOrdersByUserId(userId);
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/{orderId}/add-items")
    public ResponseEntity<Void> addItemsToOrder(@PathVariable Long orderId, @RequestBody List<Book> books) {
        userOrderService.addItemsToOrder(orderId, books);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<UserOrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody UserOrderDTO userOrderDTO) {
        try {
            // Print the values you want to debug
            System.out.println("Order ID: " + orderId);
            System.out.println("Order Status: " + userOrderDTO.getOrderStatus());
            System.out.println("Total Price: " + userOrderDTO.getTotalPrice());

            UserOrderDTO updatedOrder = userOrderService.updateOrder(orderId, userOrderDTO);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            e.printStackTrace();  // Print the full stack trace to the console
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) {
        try {
            userOrderService.deleteOrder(orderId);
            return ResponseEntity.ok("Order deleted");
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
