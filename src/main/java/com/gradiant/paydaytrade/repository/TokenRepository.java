package com.gradiant.paydaytrade.repository;

import com.gradiant.paydaytrade.entity.AuthenticationToken;
import com.gradiant.paydaytrade.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.naming.AuthenticationException;

public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
}
