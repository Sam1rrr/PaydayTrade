//package com.gradiant.paydaytrade.service.email.emailConfirmation;
//
//import com.gradiant.paydaytrade.service.inter.ConfirmationTokenServiceInter;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class ConfirmationTokenService implements ConfirmationTokenServiceInter {
//    private final ConfirmationTokenRepository confirmationTokenRepository;
//    public void saveConfirmationToken(ConfirmationToken confirmationToken){
//        confirmationTokenRepository.save(confirmationToken);
//
//    }
//    public Optional<ConfirmationToken> getToken(String token) {
//        return confirmationTokenRepository.findByToken(token);
//    }
//
//    public int setConfirmedAt(String token) {
//        return confirmationTokenRepository.updateConfirmedAt(
//                token, LocalDateTime.now());
//    }
//
//}
