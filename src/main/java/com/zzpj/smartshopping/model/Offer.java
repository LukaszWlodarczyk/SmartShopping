package com.zzpj.smartshopping.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int offerId;
    private String offerName;
    private int sellerId;
    private String sellerName;
    private boolean lowestDeliveryPrice;
    private double productPrice;
    private double expectedPrice;
    private String currency;
    private int numberOfAvailableUnits;
    private int categoryId;
    private String categoryName;
    private boolean isGoodPrice;

}
