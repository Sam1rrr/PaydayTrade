package com.gradiant.paydaytrade.controller;

import com.gradiant.paydaytrade.common.ApiResponse;
import com.gradiant.paydaytrade.entity.Stocks;
import com.gradiant.paydaytrade.entity.User;
import com.gradiant.paydaytrade.service.AuthenticationService;
import com.gradiant.paydaytrade.service.inter.WalletServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/wallet")
@RequiredArgsConstructor
public class WalletController {
    private User user;
    private final WalletServiceInter walletServiceInter;


    @PostMapping("/addStock")
        public ResponseEntity<ApiResponse> addStock(@RequestBody Stocks stocks) {
//            //authenticate token
//            authenticationService.authenticate(token);
//            //find the user
//            User user=authenticationService.getUser(token);
//            walletServiceInter.addStock(addToWalletDto,user);
            return new ResponseEntity<>(new ApiResponse(true,"Added to cart"), HttpStatus.OK);


    }
}
