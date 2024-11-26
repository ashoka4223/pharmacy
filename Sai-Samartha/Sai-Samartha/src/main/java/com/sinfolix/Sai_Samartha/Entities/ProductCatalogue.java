package com.sinfolix.Sai_Samartha.Entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ProductCatalogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private int quantity;
    private int price;
    private int discount;
    private String companyName;
    private String medicineName;
    private int minAge;
    private int maxAge;
    private int realMrp;
    private int discountMrp;
    private String prodDescription;
    private String comments;
    private  List<String> categories = new ArrayList<>();

}
