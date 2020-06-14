package com.zzpj.smartshopping.controllers;
import static org.junit.jupiter.api.Assertions.*;
import com.zzpj.smartshopping.model.Offer;
import com.zzpj.smartshopping.repositories.*;
import com.zzpj.smartshopping.services.AllegroService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OfferControllerTest {
    @Mock
    private AllegroService allegroService = mock(AllegroService.class);
    private CategoryRepository categoryRepository = mock(CategoryRepository.class);
    private OfferRepository offerRepository = mock(OfferRepository.class);
    @InjectMocks
    private OfferController offerController = new OfferController();

    @Test
    void addOffer_newOffer_shouldAddOffer() {
        MockitoAnnotations.initMocks(this);
        ResponseEntity<Offer> responseEntity;

        Offer expectedOffer = new Offer.Builder(123L,
                "url",
                "name",
                "dispName",
                500,
                4,
                "Elektronika").build();

        Optional<Offer> offerOptional = Optional.empty();

        ReflectionTestUtils.setField(expectedOffer, "isGoodPrice", false);
        ReflectionTestUtils.setField(expectedOffer, "isFavourite", false);
        ReflectionTestUtils.setField(expectedOffer, "expectedPrice", 400);
        ReflectionTestUtils.setField(offerController, "categoryRepository", categoryRepository);

        when(allegroService.getSearchedOfferFromAllegro(any(), any(), any(), any()))
                .thenReturn(expectedOffer);

        when(offerRepository.findById(any())).thenReturn(offerOptional);

        responseEntity = offerController.addOffer(123L,
                "url",
                "searchedPhrase",
                "dispName",
                400);
        Offer actualOffer = responseEntity.getBody();

        assertEquals(expectedOffer, actualOffer);
    }

    @Test
    void addOffer_alreadyExistingOffer_shouldReturnImUsed() {
        MockitoAnnotations.initMocks(this);
        ResponseEntity<Offer> actualResponseEntity;
        ResponseEntity<Offer> expectedResponseEntity = ResponseEntity.status(HttpStatus.IM_USED).build();

        Offer expectedOffer = new Offer.Builder(123L,
                "url",
                "name",
                "dispName",
                500,
                4,
                "Elektronika").build();

        Optional<Offer> offerOptional = Optional.of(expectedOffer);

        when(allegroService.getSearchedOfferFromAllegro(any(), any(), any(), any()))
                .thenReturn(expectedOffer);

        when(offerRepository.findById(any())).thenReturn(offerOptional);

        actualResponseEntity = offerController.addOffer(123L,
                "url",
                "searchedPhrase",
                "dispName",
                400);

        assertEquals(expectedResponseEntity, actualResponseEntity);
    }

    @Test
    void addOffer_nullResponseFromApi_shouldReturnNotFound() {
        MockitoAnnotations.initMocks(this);
        ResponseEntity<Offer> actualResponseEntity;
        ResponseEntity<Offer> expectedResponseEntity = ResponseEntity.notFound().build();

        when(allegroService.getSearchedOfferFromAllegro(any(), any(), any(), any()))
                .thenReturn(null);

        actualResponseEntity = offerController.addOffer(123L,
                "url",
                "searchedPhrase",
                "dispName",
                400);

        assertEquals(expectedResponseEntity, actualResponseEntity);
    }

    @Test
    void changeFavourite_offerExists_shouldChange() {
        MockitoAnnotations.initMocks(this);
        ResponseEntity<Offer> responseEntity;

        Offer offer = new Offer.Builder(123L,
                "url",
                "name",
                "dispName",
                500,
                4,
                "Elektronika").build();

        Offer expectedOffer = new Offer.Builder(123L,
                "url",
                "name",
                "dispName",
                500,
                4,
                "Elektronika").build();
        System.out.println(expectedOffer);

        Optional<Offer> offerOptional = Optional.ofNullable(offer);

        ReflectionTestUtils.setField(offer, "isFavourite", false);
        ReflectionTestUtils.setField(expectedOffer, "isFavourite", true);


        when(offerRepository.findById(any())).thenReturn(offerOptional);

        responseEntity = offerController.changeFavourite(123L);
        Offer actualOffer = responseEntity.getBody();

        assertEquals(expectedOffer, actualOffer);
    }
}