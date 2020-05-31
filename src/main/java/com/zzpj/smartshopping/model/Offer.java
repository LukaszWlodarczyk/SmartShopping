package com.zzpj.smartshopping.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Offer {

    @Id
    private Long id;

    private String offerName;
    private String displayedName;
    private String offerUrl;
    private double productPrice;
    private double expectedPrice;
    private int numberOfAvailableUnits;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private Category category;

    private Boolean isGoodPrice;
    private Boolean isFavourite;
    private Boolean isActive;

    public Offer(Long id,
                 String offerUrl,
                 String offerName,
                 String displayedName,
                 double productPrice,
                 int numberOfAvailableUnits,
                 Category category) {
        this.id = id;
        this.offerUrl = offerUrl;
        this.offerName = offerName;
        this.displayedName = displayedName;
        this.productPrice = productPrice;
        this.numberOfAvailableUnits = numberOfAvailableUnits;
        this.category = category;
        this.isActive = true;
    }


}
