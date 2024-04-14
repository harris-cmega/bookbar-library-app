package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.UserDto;
import io.bookbar.bookbarbackend.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()

        );
    }

    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole()
        );
    }
}
