package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.UserSubscriptionDto;
import io.bookbar.bookbarbackend.service.UserSubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-subscriptions")
public class UserSubscriptionController {
    private UserSubscriptionService userSubscriptionService;

    // Build Add User Subscription REST API
    @PostMapping
    public ResponseEntity<UserSubscriptionDto> createUserSubscription(@RequestBody UserSubscriptionDto userSubscriptionDto){
        UserSubscriptionDto savedUserSubscription = userSubscriptionService.createUserSubscription(userSubscriptionDto);
        return new ResponseEntity<>(savedUserSubscription, HttpStatus.CREATED);
    }

    // Build Get User Subscription REST API
    @GetMapping("{id}")
    public ResponseEntity<UserSubscriptionDto> getUserSubscriptionById(@PathVariable("id") Long id){
        UserSubscriptionDto userSubscriptionDto = userSubscriptionService.getUserSubscriptionById(id);
        return ResponseEntity.ok(userSubscriptionDto);
    }

    // Build Get All User Subscriptions REST API
    @GetMapping
    public ResponseEntity<List<UserSubscriptionDto>> getAllUserSubscriptions(){
        List<UserSubscriptionDto> userSubscriptions = userSubscriptionService.getAllUserSubscription();
        return ResponseEntity.ok(userSubscriptions);
    }

    // Build Update User Subscription REST API
    @PutMapping("{id}")
    public ResponseEntity<UserSubscriptionDto> updatedUserSubscription(@PathVariable("id") Long id, @RequestBody UserSubscriptionDto updatedUserSubscription){
        UserSubscriptionDto userSubscriptionDto = userSubscriptionService.updateUserSubscription(id, updatedUserSubscription);
        return ResponseEntity.ok(userSubscriptionDto);
    }

    // Build Delete User Subcription REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserSubscription(@PathVariable("id") Long id){
        userSubscriptionService.deleteUserSubscription(id);
        return  ResponseEntity.ok("User Subscription deleted successfully!");
    }
}
