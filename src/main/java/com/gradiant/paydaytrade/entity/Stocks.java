package com.gradiant.paydaytrade.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Stocks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String icon;
    @Column
    private String name;
    @Column
    private Double price;


}
