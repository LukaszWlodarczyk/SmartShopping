package com.zzpj.smartshopping.services;

import com.zzpj.smartshopping.model.Offer;

public interface AllegroService {
    String getToken();

    Offer getSearchedOfferFromAllegro(String offerId, String searchedPhrase, String displayedName);
}
