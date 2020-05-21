package com.zzpj.smartshopping.controllers;

import com.zzpj.smartshopping.model.Offer;
import com.zzpj.smartshopping.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @GetMapping("/")
    public ResponseEntity<Iterable<Offer>> getOffers() {
        return ResponseEntity.ok(offerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id){
        Optional<Offer> offer = offerRepository.findById(id);
        if(offer.isPresent())
            return ResponseEntity.ok(offer.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping(params ="seq")
    public List<Offer> getOfferBySequence(@RequestParam String seq){
        return offerRepository.findOffersByOfferName(seq);
    }
}
