package com.zzpj.smartshopping.services;

import com.zzpj.smartshopping.model.Offer;

public interface AllegroService {
    public String getToken();

    public Offer getSearchedOfferFromAllegro(String productId, String searchedPhrase);
}
