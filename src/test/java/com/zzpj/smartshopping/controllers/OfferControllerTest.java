package com.zzpj.smartshopping.services.impl;

import com.zzpj.smartshopping.controllers.OfferController;
import com.zzpj.smartshopping.model.Offer;
import com.zzpj.smartshopping.services.AllegroService;
import org.assertj.core.api.Assert;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class OfferControllerTest {

    @Mock
    private OfferController offerController = mock(OfferController.class);

    @InjectMocks
    private AllegroService allegroService = mock(AllegroService.class);

    @Test
    void getSearchedOfferFromAllegro() {
        ResponseEntity<Offer> myEntity;
        Offer offer = new Offer(123L,
                "url",
                "name",
                "dispName",
                500,
                4,
                "Elektronika");

        ReflectionTestUtils.setField(offer,"isGoodPrice", false);
        ReflectionTestUtils.setField(offer,"isFavourite", false);

                when(allegroService.getSearchedOfferFromAllegro(notNull(), any(), any(), any()))
                .thenReturn(offer);

        myEntity = offerController.addOffer(123L,
                "url",
                "searchedPhrase",
                "dispName",
                400);

        System.out.println(myEntity);
        //System.out.println(offer + "odebrany " + offerFrom );
        //assertEquals(offer, myEntity.getBody());
    }
}