package com.gradiant.paydaytrade.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yahoofinance.Stock;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (targetEntity = Stocks.class)
    @JoinColumn(name = "stock_id")
    private Stocks stocks;

}
