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
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.mock;


class AllegroServiceImplTest {

    @Mock
    ResponseEntity responseEntity = mock(ResponseEntity.class);
    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    private AllegroServiceImpl allegroService = mock(AllegroServiceImpl.class);

    private Offer expectedOffer = null;

    @Test
    void getSearchedOfferFromAllegro() {

        MockitoAnnotations.initMocks(this);
        responseEntity = new ResponseEntity<>("{\"items\":{\"promoted\":[{\"id\":\"8718520693\",\"name\":\"Apple\",\"seller\":{},\"promotion\":{},\"delivery\":{},\"images\":[],\"sellingMode\":{\"format\":\"BUY_NOW\",\"price\":{\"amount\":\"3499.99\",\"currency\":\"PLN\"},\"popularity\":0},\"stock\":{\"unit\":\"UNIT\",\"available\":8},\"category\":{}},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}],\"regular\":[]},\"searchMeta\":{},\"categories\":{\"subcategories\":[{\"id\":\"77915\",\"name\":\"Apple\",\"count\":480}],\"path\":[]},\"filters\":[],\"sort\":[]}",
                HttpStatus.ACCEPTED);
        HttpHeaders headers = new HttpHeaders();
        headers.add("ACCEPT", "application/vnd.allegro.public.v1+json; charset=utf-8");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        Mockito
                .when(restTemplate.exchange("https://api.allegro.pl/offers/listing?phrase=Apple&searchMode=REGULAR",
                        HttpMethod.GET,
                        entity,
                        String.class))
                .thenReturn(responseEntity);

        Offer actualOffer = allegroService.getSearchedOfferFromAllegro("8718520693",
                "url",
                "Apple",
                "MacBook");

        Assert.assertEquals(expectedOffer, actualOffer);
    }
}