package com.zzpj.smartshopping.controllers;

import com.zzpj.smartshopping.model.Offer;
import com.zzpj.smartshopping.model.OfferHistory;
import com.zzpj.smartshopping.repositories.OfferHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offershistory")
public class OfferHistoryController {

    @Autowired
    OfferHistoryRepository offerHistoryRepository;

    @GetMapping("/{id}")
    public List<OfferHistory> getOfferById(@PathVariable Long id) {
        return offerHistoryRepository.findAllByOfferId(id);
    }
}
