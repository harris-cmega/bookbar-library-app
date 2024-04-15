package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.UserDto;
import io.bookbar.bookbarbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @PostMapping

    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        if (userDto.getPassword() != null) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userDto.getPassword());
            userDto.setPassword(encryptedPassword);
        }
       UserDto savedUser = userService.createUser(userDto);
       return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
       UserDto userDto =  userService.getUserById(userId);
       return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,@RequestBody UserDto updatedUser){
        if (updatedUser.getPassword() != null) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(updatedUser.getPassword());
            updatedUser.setPassword(encryptedPassword);
        }
        UserDto userDto = userService.updateUser(userId, updatedUser);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully!");
    }
}
