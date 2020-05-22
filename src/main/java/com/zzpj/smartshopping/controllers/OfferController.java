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


    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id){
        Optional<Offer> offer = offerRepository.findById(id);
        if(offer.isPresent())
            return ResponseEntity.ok(offer.get());
        else
            return ResponseEntity.notFound().build();
    }


    //TODO: check if sortParam exists in SortingParameter table and check direction return not Found
    @GetMapping
    public List<Offer> getOffers(@RequestParam(required=false) String sortParam,@RequestParam(required=false) String direction){
        Optional<Sort.Direction> dir = Sort.Direction.fromOptionalString(direction);
        if(dir.isPresent())
            return offerRepository.findAllByOrderByIsFavouriteDesc(Sort.by(dir.get(), sortParam));
        else {
            return offerRepository.findAllByOrderByIsFavouriteDesc();
        }
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
        Optional<Sort.Direction> dir = Sort.Direction.fromOptionalString(direction);
        if(dir.isPresent())
            return offerRepository.findOffersByCategoryIdOrderByIsFavouriteDesc(categoryId,Sort.by(dir.get(), sortParam));
        else{
            return offerRepository.findOffersByCategoryIdOrderByIsFavouriteDesc(categoryId);
        }
    }

    /***CategoryName***/
    @GetMapping(params="categoryName")
    public List<Offer> getOffersByCategoryName(@RequestParam String categoryName,@RequestParam(required=false) String sortParam, @RequestParam(required=false) String direction){
        Optional<Sort.Direction> dir = Sort.Direction.fromOptionalString(direction);
        if(dir.isPresent())
            return offerRepository.findOffersByCategoryNameOrderByIsFavouriteDesc(categoryName,Sort.by(dir.get(), sortParam));
        else{
            return offerRepository.findOffersByCategoryNameOrderByIsFavouriteDesc(categoryName);
        }
    }

    /***GoodPrice***/
    @GetMapping(value="/goodPrice")
    public List<Offer> getOffersWithGoodPrice(@RequestParam(required=false) String sortParam, @RequestParam(required=false) String direction){
        Optional<Sort.Direction> dir = Sort.Direction.fromOptionalString(direction);
        if(dir.isPresent())
            return offerRepository.findByIsGoodPriceTrueOrderByIsFavouriteDesc(Sort.by(dir.get(), sortParam));
        else
            return offerRepository.findByIsGoodPriceTrueOrderByIsFavouriteDesc();
    }

    /***WithGoodPriceAndCategoryId***/
    @GetMapping(value="/goodPrice", params="categoryId")
    public List<Offer> getOffersWithGoodPriceByCategoryId( @RequestParam Long categoryId,@RequestParam(required=false) String sortParam, @RequestParam(required=false) String direction){
        Optional<Sort.Direction> dir = Sort.Direction.fromOptionalString(direction);
        if(dir.isPresent())
            return offerRepository.findByIsGoodPriceTrueAndCategoryIdOrderByIsFavouriteDesc(categoryId, Sort.by(dir.get(), sortParam));
        else
            return offerRepository.findByIsGoodPriceTrueAndCategoryIdOrderByIsFavouriteDesc(categoryId);
    }

    /***WithGoodPriceAndCategoryName***/
    @GetMapping(value="/goodPrice", params="categoryName")
    public List<Offer> getOffersWithGoodPriceByCategoryName( @RequestParam String categoryName,@RequestParam(required=false) String sortParam, @RequestParam(required=false) String direction){
        Optional<Sort.Direction> dir = Sort.Direction.fromOptionalString(direction);
        if(dir.isPresent())
            return offerRepository.findByIsGoodPriceTrueAndCategoryNameOrderByIsFavouriteDesc(categoryName, Sort.by(dir.get(), sortParam));
        else
            return offerRepository.findByIsGoodPriceTrueAndCategoryNameOrderByIsFavouriteDesc(categoryName);
    }

}


//TODO: wyszukiwanie w dobrej cenie i wyszukiwanie w kategorii
