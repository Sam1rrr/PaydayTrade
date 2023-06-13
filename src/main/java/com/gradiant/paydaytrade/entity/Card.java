//package com.gradiant.paydaytrade.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Table(name = "card")
//@Data
//public class Card {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    private String cartType;//Mastercard Visa
//    private String cardNumber;
//    private String holderName;
//    private Integer cvv;
//    private Date expiredDate;
//    private BigDecimal balance;
//
//    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "user_balance")
//    private User user;
//
//
//
//
//}
