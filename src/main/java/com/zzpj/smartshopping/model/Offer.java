package com.zzpj.smartshopping.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class Offer {

    @Id
    @NotNull
    @Positive
    private Long id;

    @NotEmpty
    private String offerName;
    @Size(min = 3, max=50, message = "Name must be between 3 and 50 characters")
    private String displayedName;
    private String offerUrl;
    @Positive(message = "price must be positive")
    private double productPrice;
    @Positive(message = "price must be positive")
    private double expectedPrice;
    @Positive(message = "price must be positive")
    private int numberOfAvailableUnits;

    @NotEmpty(message = "category must not be empty")
    private String category;

    private Boolean isGoodPrice;
    private Boolean isFavourite;
    private Boolean isActive;

    public Offer(Long id,
                 String offerUrl,
                 String offerName,
                 String displayedName,
                 double productPrice,
                 int numberOfAvailableUnits,
                 String category) {
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
