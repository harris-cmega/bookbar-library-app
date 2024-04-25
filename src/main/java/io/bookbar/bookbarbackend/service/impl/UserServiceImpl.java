package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.UserDto;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.UserMapper;
import io.bookbar.bookbarbackend.repository.UserRepository;
import io.bookbar.bookbarbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {

        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
       User user =  userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given id does not exist: " + userId));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
       List<User> users = userRepository.findAll();

        return users.stream().map((user) -> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUser) {
       User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with this id does not exist " + userId)
        );

       user.setEmail(updatedUser.getEmail());
       user.setUsername(updatedUser.getUsername());
       user.setPassword(updatedUser.getPassword());
       user.setBalance(updatedUser.getBalance());
       user.setCity(updatedUser.getCity());
       user.setCountry(updatedUser.getCountry());
       user.setStreet(updatedUser.getStreet());

      User updatedUserObj =  userRepository.save(user);

        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId) {

        System.out.println("here");
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User with this id does not exist " + userId)
        );
        System.out.println("here %s" + user);

        userRepository.deleteById(userId);

    }

}
