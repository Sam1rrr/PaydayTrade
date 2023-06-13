package com.gradiant.paydaytrade.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;


@Entity
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        @Table(name = "token")
public class AuthenticationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String token;
    @Column(name = "created_date")
    private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public AuthenticationToken(User user) {
        this.createdDate = new Date();
        this.user = user;
        this.token = UUID.randomUUID().toString();

    }


}
