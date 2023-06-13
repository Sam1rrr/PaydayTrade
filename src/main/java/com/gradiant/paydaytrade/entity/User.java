package com.gradiant.paydaytrade.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "mail")
    private String mail;
    @Column(name = "number")
    private String number;
    @Column(name = "password")
    private String password;
    @Column(name="balance")
    private BigDecimal balance;
    @Column(name ="profile_picture")
    private String picture;

    private Boolean enabled = false;


//     @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<Card> cards;

 @OneToOne(targetEntity = Wallet.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
 private Wallet wallet;




    public User(String name, String surname, String encryptedPassword, String mail, String number, BigDecimal balance) {
    }
}
