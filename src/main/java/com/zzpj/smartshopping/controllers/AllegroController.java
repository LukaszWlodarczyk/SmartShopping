package com.zzpj.smartshopping.controllers;

import com.zzpj.smartshopping.model.Offer;
import com.zzpj.smartshopping.services.AllegroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apiAllegro")
public class AllegroController {
    @Autowired
    AllegroService allegroService;

    @GetMapping(params = {"offerId", "searchedPhrase"})
    public ResponseEntity<Offer> getSearchedProduct(
            @RequestParam String offerId,
            @RequestParam String offerUrl,
            @RequestParam String searchedPhrase,
            @RequestParam String displayedName) {

        Offer searchedOffer = allegroService.getSearchedOfferFromAllegro(offerId,
                offerUrl,
                searchedPhrase,
                displayedName);
        if (searchedOffer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(searchedOffer);
        }
    }
}
