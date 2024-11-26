package com.sinfolix.Sai_Samartha.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCatalogueDTO {

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
    private List<String> categories = new ArrayList<>();
}
