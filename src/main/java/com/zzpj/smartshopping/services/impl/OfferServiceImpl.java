package com.zzpj.smartshopping.services.impl;

import com.zzpj.smartshopping.model.Offer;
import com.zzpj.smartshopping.model.SortingParameter;
import com.zzpj.smartshopping.repositories.OfferRepository;
import com.zzpj.smartshopping.repositories.SortingParameterRepository;
import com.zzpj.smartshopping.services.AllegroService;
import com.zzpj.smartshopping.services.OfferService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.TimerTask;

@Service
public class OfferServiceImpl extends TimerTask implements OfferService {

    @Autowired
    private SortingParameterRepository sortingParameterRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private AllegroService allegroService;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public boolean checkSortingParameters(String sortParam, String direction) {
        Optional<Sort.Direction> dir = Sort.Direction.fromOptionalString(direction);
        Optional<SortingParameter> param = sortingParameterRepository.findByName(sortParam);
        return dir.isPresent() && param.isPresent();
    }


    public boolean updateOffer(Offer offer) {
        Offer updatedOffer = allegroService.getSearchedOfferFromAllegro(String.valueOf(offer.getId()), offer.getOfferName(), offer.getDisplayedName());
        if (updatedOffer != null) {
            offer.setOfferName(updatedOffer.getOfferName());
            offer.setDisplayedName(updatedOffer.getDisplayedName());
            offer.setCategory(updatedOffer.getCategory());
            offer.setNumberOfAvailableUnits(updatedOffer.getNumberOfAvailableUnits());
            offer.setProductPrice(updatedOffer.getProductPrice());
            offer.setIsGoodPrice(offer.getProductPrice() <= offer.getExpectedPrice());
            offerRepository.save(offer);
            return true;
        } else return false;

    }

    @Override
    public void run() {
        List<Offer> offers = offerRepository.findAll();
        for (Offer offer : offers) {
                if(!updateOffer(offer)) {
                offer.setIsActive(false);
                offerRepository.save(offer);
            }

        }
    }
}
