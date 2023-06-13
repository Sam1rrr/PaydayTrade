package com.gradiant.paydaytrade.service.email.emailConfirmation;

import com.gradiant.paydaytrade.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)


    private Long id;
    @Column(nullable = false)

    private String token;
    @Column(nullable = false)

    private LocalDateTime createdAt;
    @Column(nullable = false)

    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private User user;
    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiredAt,
                             User user) {

        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.user=user;
    }
    public ConfirmationToken(User user){
    this.user=user;
    }
}
