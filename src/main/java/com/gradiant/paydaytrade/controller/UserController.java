package com.gradiant.paydaytrade.controller;

import com.gradiant.paydaytrade.common.ApiResponse;
import com.gradiant.paydaytrade.dto.user.SignInDto;
import com.gradiant.paydaytrade.dto.user.UserDto;
import com.gradiant.paydaytrade.entity.User;
import com.gradiant.paydaytrade.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userServiceInter;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody UserDto sigupDto) {
        userServiceInter.signUp(sigupDto);
        return new ResponseEntity<>(new ApiResponse(true, "user has been created"), HttpStatus.CREATED);

    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse> signin(@RequestBody SignInDto signInDto) {
        userServiceInter.signin(signInDto);
        return new ResponseEntity<>(new ApiResponse(true, "user login successfull"), HttpStatus.OK);
    }

//    @PatchMapping("/update/{id}")
//    public ResponseEntity<ApiResponse> editUser(@RequestBody UserDto userdto, @PathVariable("id") Integer id) {
//        if (userServiceInter.findById(id)) {
//            return new ResponseEntity<>(new ApiResponse(false, "user does not exist"), HttpStatus.NOT_FOUND);
//        }
//        userServiceInter.updateUser(id, userdto);
//        return new ResponseEntity<>(new ApiResponse(true, "user has been updated"), HttpStatus.ACCEPTED);
//    }

    @PostMapping("/addProfilePicture")
    public ResponseEntity<ApiResponse> addPicture(@RequestParam MultipartFile file) {
        userServiceInter.profilPhoto(file);
        return new ResponseEntity<>(new ApiResponse(true, "profile picture has been updated"), HttpStatus.OK);
    }
//    @GetMapping("/confirm")
//    public String confirm(@RequestParam("token") String token) {
//        return userServiceInter.confirmToken(token);
//    }


}
