package com.gradiant.paydaytrade.service;

//import com.gradiant.paydaytrade.dto.card.CardDto;
import com.gradiant.paydaytrade.dto.card.DepositDto;
import com.gradiant.paydaytrade.dto.user.UserDto;
import com.gradiant.paydaytrade.entity.User;
import com.gradiant.paydaytrade.repository.UserRepository;
import com.gradiant.paydaytrade.service.inter.CardServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService implements CardServiceInter  {
    private final UserRepository userRepository;
    private  User user;




//       public UserDto loadMoney(Integer id, DepositDto depositDto){
//User user=findById(id);
//user.setBalance(user.getBalance().add(depositDto.getAmount()));
//
//return userRepository.save(user);
//    }


    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
}
