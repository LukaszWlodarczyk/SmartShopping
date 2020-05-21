package com.zzpj.smartshopping.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class OfferHistory {

    @Id
    private Long id;


    private int offerId;
    private double price;
    private LocalDateTime date;
}
