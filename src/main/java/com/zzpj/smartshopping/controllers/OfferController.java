package com.zzpj.smartshopping.controllers;

import com.zzpj.smartshopping.model.Offer;
import com.zzpj.smartshopping.repositories.OfferRepository;
import com.zzpj.smartshopping.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    OfferService offerService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable Long id){
        Optional<Offer> offer = offerRepository.findById(id);
        if(offer.isPresent()) {
            offerRepository.deleteById(id);
            return ResponseEntity.ok(offer.get());
        }
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id){
        Optional<Offer> offer = offerRepository.findById(id);
        if(offer.isPresent())
            return ResponseEntity.ok(offer.get());
        else
            return ResponseEntity.notFound().build();
    }


    @GetMapping
    public List<Offer> getOffers(@RequestParam(required=false) String sortParam,@RequestParam(required=false) String direction){
        if(sortParam==null && direction==null)
            return offerRepository.findAllByOrderByIsFavouriteDesc();
        else if(offerService.checkSortingParameters(sortParam,direction))
            return offerRepository.findAllByOrderByIsFavouriteDesc(Sort.by(Sort.Direction.fromString(direction), sortParam));
        else return Collections.emptyList();
    }

   /*******************GetOffersBy***********************/

    /***forSearching***/
    @GetMapping(params ="seq")
    public List<Offer> getOffersBySequence(@RequestParam String seq){
        return offerRepository.findOffersByOfferNameOrderByIsFavouriteDesc(seq);
    }

    /***CategoryId***/
    @GetMapping(params="categoryId")
    public List<Offer> getOffersByCategoryId(@RequestParam Long categoryId,@RequestParam(required=false) String sortParam, @RequestParam(required=false) String direction){
        if(sortParam==null && direction==null)
            return offerRepository.findOffersByCategoryIdOrderByIsFavouriteDesc(categoryId);
        else if(offerService.checkSortingParameters(sortParam,direction))
            return offerRepository.findOffersByCategoryIdOrderByIsFavouriteDesc(categoryId,Sort.by(Sort.Direction.fromString(direction), sortParam));
        else return Collections.emptyList();
    }

    /***CategoryName***/
    @GetMapping(params="categoryName")
    public List<Offer> getOffersByCategoryName(@RequestParam String categoryName,@RequestParam(required=false) String sortParam, @RequestParam(required=false) String direction){
        if(sortParam==null && direction==null)
            return offerRepository.findOffersByCategoryNameOrderByIsFavouriteDesc(categoryName);
        else if(offerService.checkSortingParameters(sortParam,direction))
            return offerRepository.findOffersByCategoryNameOrderByIsFavouriteDesc(categoryName,Sort.by(Sort.Direction.fromString(direction), sortParam));
        else return Collections.emptyList();
    }

    /***GoodPrice***/
    @GetMapping(value="/goodPrice")
    public List<Offer> getOffersWithGoodPrice(@RequestParam(required=false) String sortParam, @RequestParam(required=false) String direction){
        if(sortParam==null && direction==null)
            return offerRepository.findByIsGoodPriceTrueOrderByIsFavouriteDesc();
        else if(offerService.checkSortingParameters(sortParam,direction))
            return offerRepository.findByIsGoodPriceTrueOrderByIsFavouriteDesc(Sort.by(Sort.Direction.fromString(direction), sortParam));
        else return Collections.emptyList();
    }

    /***WithGoodPriceAndCategoryId***/
    @GetMapping(value="/goodPrice", params="categoryId")
    public List<Offer> getOffersWithGoodPriceByCategoryId( @RequestParam Long categoryId,@RequestParam(required=false) String sortParam, @RequestParam(required=false) String direction){
        if(sortParam==null && direction==null)
            return offerRepository.findByIsGoodPriceTrueAndCategoryIdOrderByIsFavouriteDesc(categoryId);
        else if(offerService.checkSortingParameters(sortParam,direction))
            return offerRepository.findByIsGoodPriceTrueAndCategoryIdOrderByIsFavouriteDesc(categoryId, Sort.by(Sort.Direction.fromString(direction), sortParam));
        else return Collections.emptyList();
    }

    /***WithGoodPriceAndCategoryName***/
    @GetMapping(value="/goodPrice", params="categoryName")
    public List<Offer> getOffersWithGoodPriceByCategoryName( @RequestParam String categoryName,@RequestParam(required=false) String sortParam, @RequestParam(required=false) String direction){
        if(sortParam==null && direction==null)
            return offerRepository.findByIsGoodPriceTrueAndCategoryNameOrderByIsFavouriteDesc(categoryName);
        else if(offerService.checkSortingParameters(sortParam,direction))
            return offerRepository.findByIsGoodPriceTrueAndCategoryNameOrderByIsFavouriteDesc(categoryName, Sort.by(Sort.Direction.fromString(direction), sortParam));
        else return Collections.emptyList();
    }

}
