package com.sinfolix.Sai_Samartha.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String customerAddress;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private LocalDate modified_time;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private boolean isPrescriptionBased;

    @Column(nullable = false)
    private boolean reviewed;

}
