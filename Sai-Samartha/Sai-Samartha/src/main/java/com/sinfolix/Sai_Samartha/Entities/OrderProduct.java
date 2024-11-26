package com.sinfolix.Sai_Samartha.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_product")
@Data
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_catalogue_id", nullable = false)
    private ProductCatalogue productCatalogue;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
