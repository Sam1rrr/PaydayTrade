package com.gradiant.paydaytrade.service.inter;

import com.gradiant.paydaytrade.common.ApiResponse;
import com.gradiant.paydaytrade.dto.user.SignInDto;
import com.gradiant.paydaytrade.dto.user.UserDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    ApiResponse signUp(UserDto signUpDto);
    ApiResponse signin(SignInDto signInDto);
   // void updateUser(Integer id, UserDto userDto);
  //boolean findById(Integer id);
     void profilPhoto(MultipartFile file);
   // UserDto loadMoney(Integer id, DepositDto depositDto);

//    String confirmToken(String token);
//ResponseEntity<ApiResponse> saveUser(User user);

String confirmEmail(String confirmationToken);

 }
