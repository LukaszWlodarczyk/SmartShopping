package com.zzpj.smartshopping.controllers;

import com.zzpj.smartshopping.model.Offer;
import com.zzpj.smartshopping.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    public List<Offer> getOffersBySequence(@RequestParam String seq){
        return offerRepository.findOffersByOfferName(seq);
    }

    @GetMapping(params ="categoryName")
    public List<Offer> getOffersByCategoryName(@RequestParam String categoryName){
        return offerRepository.findOffersByCategoryName(categoryName);
    }

    @GetMapping(params ="categoryId")
    public List<Offer> getOffersByCategoryId(@RequestParam Long categoryId){
        return offerRepository.findOffersByCategoryId(categoryId);
    }

    @GetMapping(value="/sortedAsc", params="parameterName")
    public List<Offer> getOffersSortedAscByParameter(String parameterName){
        if(parameterName.equals("isGoodPrice") || parameterName.equals("isFavourite"))
            return offerRepository.findAll(Sort.by(Sort.Direction.DESC, parameterName));
        else
            return offerRepository.findAll(Sort.by(Sort.Direction.ASC, parameterName));
    }

    @GetMapping(value="/sortedDesc", params="parameterName")
    public List<Offer> getOffersSortedDescByParameter(String parameterName){
        if(parameterName.equals("isGoodPrice") || parameterName.equals("isFavourite"))
            return offerRepository.findAll(Sort.by(Sort.Direction.ASC, parameterName));
        else
            return offerRepository.findAll(Sort.by(Sort.Direction.DESC, parameterName));
    }

}
