package com.zzpj.smartshopping.services.impl;

import com.zzpj.smartshopping.model.Offer;
import com.zzpj.smartshopping.services.AllegroService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class AllegroServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AllegroServiceImpl allegroService = new AllegroServiceImpl();

    @Test
    void getSearchedOfferFromAllegro() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("ACCEPT", "application/vnd.allegro.public.v1+json; charset=utf-8");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<List<String>> myEntity = new ResponseEntity<List<String>>(HttpStatus.ACCEPTED);
        Offer offer = new Offer(123L,
                "url",
                "name",
                "dispName",
                500,
                4,
                "Elektronika");
        String jsonString = new JSONObject()
                .put("id", "123")
                .put("name", "name")
                .toString();

        Mockito
                .doReturn(myEntity)
                .when(restTemplate.exchange("https://api.allegro.pl/offers/listing?phrase=searchedPhrase&searchMode=REGULAR",
                HttpMethod.GET,
                entity,
                String.class));

        Offer offerFromService = allegroService.getSearchedOfferFromAllegro("123",
                "url",
                "searchedPhrase",
                "xd" );

        Assert.assertTrue(offerFromService.equals(offer));
        //Assert.assertArrayEquals(offer, offerFromService);
    }
}