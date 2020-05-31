package com.zzpj.smartshopping.services;

import com.zzpj.smartshopping.model.Offer;

public interface OfferService {
    boolean checkSortingParameters(String sortParam, String direction);

    boolean updateOffer(Offer offer);

}
