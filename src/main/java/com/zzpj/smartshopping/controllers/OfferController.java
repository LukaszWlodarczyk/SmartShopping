package com.zzpj.smartshopping.controllers;

import com.zzpj.smartshopping.model.Offer;
import com.zzpj.smartshopping.repositories.OfferRepository;
import com.zzpj.smartshopping.services.AllegroService;
import com.zzpj.smartshopping.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    AllegroService allegroService;

    @Autowired
    OfferService offerService;

    /*************add new offer*******************/
    @PostMapping(value = "/{id}", params = {"searchedPhrase", "expectedPrice"})
    public ResponseEntity<Offer> addOffer(@PathVariable Long id, @RequestParam String searchedPhrase, @RequestParam String displayedName, @RequestParam double expectedPrice) {
        Offer offer = allegroService.getSearchedOfferFromAllegro(Long.toString(id), searchedPhrase, displayedName);
        if (offer != null) {
            if (!offerRepository.findById(id).isPresent()) {
                offer.setExpectedPrice(expectedPrice);
                offer.setIsGoodPrice(offer.getProductPrice() <= offer.getExpectedPrice());
                offerRepository.save(offer);
                return ResponseEntity.ok(offer);
            } else return ResponseEntity.status(HttpStatus.IM_USED).build();
        } else
            return ResponseEntity.notFound().build();
    }

    /************add/delete from favourite***********************/
    @PutMapping(value = "/{id}")
    public ResponseEntity<Offer> changeFavourite(@PathVariable Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            offer.get().setIsFavourite(!offer.get().getIsFavourite());
            offerRepository.save(offer.get());
            return ResponseEntity.ok(offer.get());
        } else
            return ResponseEntity.notFound().build();
    }

    /************change expected price*********************/
    @PutMapping(value = "/{id}", params = "expectedPrice")
    public ResponseEntity<Offer> changeExpectedPrice(@PathVariable Long id, @RequestParam double expectedPrice) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            offer.get().setExpectedPrice(expectedPrice);
            offer.get().setIsGoodPrice(offer.get().getProductPrice() <= offer.get().getExpectedPrice());
            offerRepository.save(offer.get());
            return ResponseEntity.ok(offer.get());
        } else
            return ResponseEntity.notFound().build();
    }

    /***********update offer from allegro*******************/
    @PutMapping(value = "/{id}/update")
    public ResponseEntity<Offer> updateOffer(@PathVariable Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            if (!offerService.updateOffer(offer.get())) {
                return ResponseEntity.status(500).build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    /*****************delete offer***************/
    @DeleteMapping("/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent()) {
            offerRepository.deleteById(id);
            return ResponseEntity.ok(offer.get());
        } else
            return ResponseEntity.notFound().build();
    }

    /************get offer by id************/
    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isPresent())
            return ResponseEntity.ok(offer.get());
        else
            return ResponseEntity.notFound().build();
    }

    /************get all offers (can be sorted)***********/
    @GetMapping
    public List<Offer> getOffers(@RequestParam(required = false) String sortParam, @RequestParam(required = false) String direction) {
        if (sortParam == null && direction == null)
            return offerRepository.findAllByOrderByIsFavouriteDesc();
        else if (offerService.checkSortingParameters(sortParam, direction))
            return offerRepository.findAllByOrderByIsFavouriteDesc(Sort.by(Sort.Direction.fromString(direction), sortParam));
        else return Collections.emptyList();
    }

    /*******************GetOffersBy***********************/

    /***forSearching***/
    @GetMapping(params = "seq")
    public List<Offer> getOffersBySequence(@RequestParam String seq) {
        return offerRepository.findOffersByOfferNameOrderByIsFavouriteDesc(seq);
    }

    /***CategoryId***/
    @GetMapping(params = "categoryId")
    public List<Offer> getOffersByCategoryId(@RequestParam Long categoryId, @RequestParam(required = false) String sortParam, @RequestParam(required = false) String direction) {
        if (sortParam == null && direction == null)
            return offerRepository.findOffersByCategoryIdOrderByIsFavouriteDesc(categoryId);
        else if (offerService.checkSortingParameters(sortParam, direction))
            return offerRepository.findOffersByCategoryIdOrderByIsFavouriteDesc(categoryId, Sort.by(Sort.Direction.fromString(direction), sortParam));
        else return Collections.emptyList();
    }

    /***CategoryName***/
    @GetMapping(params = "categoryName")
    public List<Offer> getOffersByCategoryName(@RequestParam String categoryName, @RequestParam(required = false) String sortParam, @RequestParam(required = false) String direction) {
        if (sortParam == null && direction == null)
            return offerRepository.findOffersByCategoryNameOrderByIsFavouriteDesc(categoryName);
        else if (offerService.checkSortingParameters(sortParam, direction))
            return offerRepository.findOffersByCategoryNameOrderByIsFavouriteDesc(categoryName, Sort.by(Sort.Direction.fromString(direction), sortParam));
        else return Collections.emptyList();
    }

    /***GoodPrice***/
    @GetMapping(value = "/goodPrice")
    public List<Offer> getOffersWithGoodPrice(@RequestParam(required = false) String sortParam, @RequestParam(required = false) String direction) {
        if (sortParam == null && direction == null)
            return offerRepository.findByIsGoodPriceTrueOrderByIsFavouriteDesc();
        else if (offerService.checkSortingParameters(sortParam, direction))
            return offerRepository.findByIsGoodPriceTrueOrderByIsFavouriteDesc(Sort.by(Sort.Direction.fromString(direction), sortParam));
        else return Collections.emptyList();
    }

    /***WithGoodPriceAndCategoryId***/
    @GetMapping(value = "/goodPrice", params = "categoryId")
    public List<Offer> getOffersWithGoodPriceByCategoryId(@RequestParam Long categoryId, @RequestParam(required = false) String sortParam, @RequestParam(required = false) String direction) {
        if (sortParam == null && direction == null)
            return offerRepository.findByIsGoodPriceTrueAndCategoryIdOrderByIsFavouriteDesc(categoryId);
        else if (offerService.checkSortingParameters(sortParam, direction))
            return offerRepository.findByIsGoodPriceTrueAndCategoryIdOrderByIsFavouriteDesc(categoryId, Sort.by(Sort.Direction.fromString(direction), sortParam));
        else return Collections.emptyList();
    }

    /***WithGoodPriceAndCategoryName***/
    @GetMapping(value = "/goodPrice", params = "categoryName")
    public List<Offer> getOffersWithGoodPriceByCategoryName(@RequestParam String categoryName, @RequestParam(required = false) String sortParam, @RequestParam(required = false) String direction) {
        if (sortParam == null && direction == null)
            return offerRepository.findByIsGoodPriceTrueAndCategoryNameOrderByIsFavouriteDesc(categoryName);
        else if (offerService.checkSortingParameters(sortParam, direction))
            return offerRepository.findByIsGoodPriceTrueAndCategoryNameOrderByIsFavouriteDesc(categoryName, Sort.by(Sort.Direction.fromString(direction), sortParam));
        else return Collections.emptyList();
    }

}
