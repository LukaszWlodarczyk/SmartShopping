package com.zzpj.smartshopping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private double productPrice;
    private double expectedPrice;
    private int numberOfAvailableUnits;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private Category category;

    private boolean isGoodPrice;
    private boolean isFavourite;
}
