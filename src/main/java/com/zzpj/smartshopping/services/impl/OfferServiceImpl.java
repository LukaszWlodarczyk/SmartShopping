package com.zzpj.smartshopping.services.impl;

import com.zzpj.smartshopping.model.Offer;
import com.zzpj.smartshopping.model.OfferHistory;
import com.zzpj.smartshopping.model.SortingParameter;
import com.zzpj.smartshopping.repositories.OfferHistoryRepository;
import com.zzpj.smartshopping.repositories.OfferRepository;
import com.zzpj.smartshopping.repositories.SortingParameterRepository;
import com.zzpj.smartshopping.services.AllegroService;
import com.zzpj.smartshopping.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TimerTask;

@Service
public class OfferServiceImpl extends TimerTask implements OfferService {

    @Autowired
    private SortingParameterRepository sortingParameterRepository;

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private OfferHistoryRepository offerHistoryRepository;

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
        Offer updatedOffer = allegroService.getSearchedOfferFromAllegro(
                String.valueOf(offer.getId()),
                offer.getOfferUrl(),
                offer.getOfferName(),
                offer.getDisplayedName());

        if (updatedOffer != null) {
            offer.setOfferName(updatedOffer.getOfferName());
            offer.setOfferUrl(updatedOffer.getOfferUrl());
            offer.setDisplayedName(updatedOffer.getDisplayedName());
            offer.setCategory(updatedOffer.getCategory());
            offer.setNumberOfAvailableUnits(updatedOffer.getNumberOfAvailableUnits());
            offer.setProductPrice(updatedOffer.getProductPrice());
            offer.setIsGoodPrice(offer.getProductPrice() <= offer.getExpectedPrice());
            offerRepository.save(offer);
            return true;
        } else return false;
    }

    //    @Scheduled(fixedDelay = 600000)
    @Scheduled(fixedDelay = 10000)
    @Override
    public void run() {
        List<Offer> offers = offerRepository.findAll();
        for (Offer offer : offers) {
            if (!updateOffer(offer)) {
                offer.setIsActive(false);
                offerRepository.save(offer);
            } else {
                List<OfferHistory> offerHistoryByOfferID = offerHistoryRepository.findAllByOfferId(offer.getId());
                if (!offerHistoryByOfferID.isEmpty()) {
                    OfferHistory lastOffer = offerHistoryByOfferID.get(offerHistoryByOfferID.size() - 1);
                    if (lastOffer.getPrice() != offer.getProductPrice()) {
                        LocalDateTime date = LocalDateTime.now();
                        OfferHistory offerHistory = new OfferHistory(offer.getId(), offer.getProductPrice(), date);
                        offerHistoryRepository.save(offerHistory);
                    }
//                Zeby pokazac ze dziala xD
//                    if (lastOffer.getPrice() == offer.getProductPrice()) {
//                        LocalDateTime date = LocalDateTime.now();
//                        OfferHistory offerHistory = new OfferHistory(offer.getId(), offer.getProductPrice(), date);
//                        offerHistoryRepository.save(offerHistory);
//                        System.out.println("Testowa funkcja zapisywania obiektow w historii");
//                    }
                } else {
                    LocalDateTime date = LocalDateTime.now();
                    OfferHistory offerHistory = new OfferHistory(offer.getId(), offer.getProductPrice(), date);
                    offerHistoryRepository.save(offerHistory);
                }
            }
        }
//        System.out.println(offerHistoryRepository.findAll());
    }
}
