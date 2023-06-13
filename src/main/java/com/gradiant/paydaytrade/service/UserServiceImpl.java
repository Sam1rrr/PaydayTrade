package com.gradiant.paydaytrade.service;

import com.gradiant.paydaytrade.common.ApiResponse;
import com.gradiant.paydaytrade.dto.user.SignInDto;
import com.gradiant.paydaytrade.dto.user.UserDto;
import com.gradiant.paydaytrade.entity.AuthenticationToken;
import com.gradiant.paydaytrade.entity.User;
import com.gradiant.paydaytrade.exception.AuthenticationFailException;
import com.gradiant.paydaytrade.exception.CustomException;
import com.gradiant.paydaytrade.repository.UserRepository;
//import com.gradiant.paydaytrade.service.email.emailConfirmation.ConfirmationToken;
//import com.gradiant.paydaytrade.service.email.emailConfirmation.ConfirmationTokenRepository;
//import com.gradiant.paydaytrade.service.email.emailConfirmation.ConfirmationTokenService;
////import com.gradiant.paydaytrade.service.email.emailConfirmation.emailSender.EmailSender;
//import com.gradiant.paydaytrade.service.email.emailConfirmation.emailSender.EmailSender;
//import com.gradiant.paydaytrade.service.inter.ConfirmationTokenServiceInter;
import com.gradiant.paydaytrade.service.email.emailConfirmation.ConfirmationToken;
import com.gradiant.paydaytrade.service.email.emailConfirmation.ConfirmationTokenRepository;
import com.gradiant.paydaytrade.service.inter.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

   private  ConfirmationTokenRepository confirmationTokenRepository;

    private  com.gradiant.paydaytrade.service.email.emailConfirmation.emailSender.EmailService emailService;




    @Override
    public String confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByUserEmailIgnoreCase(token.getUser().getMail());
            user.setEnabled(true);
            userRepository.save(user);
            return "Email verified successfully!";
        }
        return "Error: Couldn't verify email";
    }






























    public ApiResponse signUp(UserDto signUpDto) {
        if (Objects.nonNull(userRepository.findByMail(signUpDto.getMail()))) {
            throw new CustomException("User already exist!");
        }
        String encryptedPassword = signUpDto.getPassword();
        try {
            encryptedPassword = hashPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = User.builder()
                .name(signUpDto.getName())
                .surname(signUpDto.getSurname())
                .mail(signUpDto.getMail())
                .number(signUpDto.getNumber())
                .password(encryptedPassword)
                .build();


        userRepository.save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getMail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8085/confirm-account?token="+confirmationToken.getToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getToken());


//        String token = UUID.randomUUID().toString();
//        ConfirmationToken confirmationToken = new ConfirmationToken(
//                token,
//                LocalDateTime.now(),
//                LocalDateTime.now().plusMinutes(15),
//                user
//
//
//        );
//        String link="http://localhost:8080/user/confirm?token="+token;
//        emailSender.send(user.getMail(),buildEmail(user.getName(),link));
//        confirmationTokenServiceInter.saveConfirmationToken(confirmationToken);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);
        ApiResponse apiResponse = new ApiResponse(true, "user has been created");

        return apiResponse;
    }

//    @Transactional
//    public String confirmToken(String token) {
//        ConfirmationToken confirmationToken = confirmationTokenService
//                .getToken(token)
//                .orElseThrow(() ->
//                        new IllegalStateException("token not found"));
//
//        if (confirmationToken.getConfirmedAt() != null) {
//            throw new IllegalStateException("email already confirmed");
//        }
//
//        LocalDateTime expiredAt = confirmationToken.getExpiredAt();
//
//        if (expiredAt.isBefore(LocalDateTime.now())) {
//            throw new IllegalStateException("token expired");
//        }
//
//        confirmationTokenService.setConfirmedAt(token);
//        confirmationToken.getUser().getMail();
//        return "confirmed";
//    }

    //  public int enableAppUser(String mail) {return userRepository.enableAppUser(mail);}

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }


    @Override
    public ApiResponse signin(SignInDto signInDto) {
        User user = userRepository.findByMail(signInDto.getMail());
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user does not exist");
        }
        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException("Wrong password!");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        AuthenticationToken authenticationToken = authenticationService.getToken(user);
        if (Objects.isNull(authenticationToken)) {
            throw new CustomException("Token is not present");
        }
        return new ApiResponse(true, authenticationToken.getToken());

    }

    @Override
    public void profilPhoto(MultipartFile file) {
        User u = new User();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            throw new CustomException("not a valid file");
        }
        try {
            u.setPicture(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        userRepository.save(u);
    }




    public void updateUser(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById((int) id);
        if (optionalUser.isEmpty()) {
            throw new CustomException("User does not exist!");
        } else {
            User user1 = optionalUser.get();
            user1.setName(userDto.getName());
            user1.setSurname(userDto.getSurname());
            user1.setMail(userDto.getMail());
            user1.setNumber(userDto.getNumber());
            user1.setPassword(userDto.getPassword());
            user1.setBalance(userDto.getBalance());
            user1.setPicture(userDto.getPicture());
            userRepository.save(user1);
        }


        //   }
//    public UserDto loadMoney(Integer id, DepositDto depositDto){
//User user=findById(id);
//user.setBalance(user.getBalance().add(depositDto.getAmount()));
//
//
//    }
//
//


//    public boolean findById(Integer id) {
//        return userRepository.findById(id);
//    }





    }
}