package com.gradiant.paydaytrade.util;

import com.gradiant.paydaytrade.dto.user.SignInDto;
import com.gradiant.paydaytrade.dto.user.UserDto;
import com.gradiant.paydaytrade.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public UserDto converter1(User user) {
        UserDto signUpDto = UserDto.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .number(user.getNumber())
                .mail(user.getMail())
                .balance(user.getBalance())
                .password(user.getPassword())
                .picture(user.getPicture())

                .build();
        return signUpDto;
    }

    public SignInDto converter2(User user) {
        SignInDto signInDto = SignInDto.builder()
                .mail(user.getMail())
                .password(user.getPassword())
                .build();
        return signInDto;
    }
}
