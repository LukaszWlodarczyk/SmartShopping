package com.zzpj.smartshopping.controllers;

import com.zzpj.smartshopping.model.Offer;
import com.zzpj.smartshopping.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @GetMapping("/offers")
    public ResponseEntity<Iterable<Offer>> getCases() {
        return ResponseEntity.ok(offerRepository.findAll());
    }
}
