package com.gradiant.paydaytrade.service;

import com.gradiant.paydaytrade.entity.AuthenticationToken;
import com.gradiant.paydaytrade.entity.User;
import com.gradiant.paydaytrade.exception.AuthenticationFailException;
import com.gradiant.paydaytrade.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
private final TokenRepository tokenRepository;
public void saveConfirmationToken(AuthenticationToken authenticationToken){
tokenRepository.save(authenticationToken);
}
public User getUser(String token){
final AuthenticationToken authenticationToken=tokenRepository.findByToken(token);
if(Objects.isNull(authenticationToken)){
return null;
}
return authenticationToken.getUser();

}
public AuthenticationToken getToken(User user){
return tokenRepository.findByUser(user);
}




    public void authenticate(String token) throws AuthenticationFailException {
        if (Objects.isNull(token)) {
            throw new AuthenticationFailException("token not present");

        }
        if (Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("token not present");

        }
    }
}