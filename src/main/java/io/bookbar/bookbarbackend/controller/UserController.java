package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.UserDto;
import io.bookbar.bookbarbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    //Build Add User REST API
    @PostMapping

    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
       UserDto savedUser = userService.createUser(userDto);
       return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //Build Get User REST API
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
       UserDto userDto =  userService.getUserById(userId);
       return ResponseEntity.ok(userDto);
    }

    //Build Get All Users REST API
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //Build Update User REST API
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,@RequestBody UserDto updatedUser){
       UserDto userDto = userService.updateUser(userId, updatedUser);
       return ResponseEntity.ok(userDto);
    }

    //Build Delete User REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully!");
    }
}
