package com.zzpj.smartshopping.services.impl;

import com.zzpj.smartshopping.model.Category;
import com.zzpj.smartshopping.model.Offer;
import com.zzpj.smartshopping.services.AllegroService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AllegroServiceImpl implements AllegroService {


    public String getToken() {
        String tokenUrl = "https://allegro.pl/auth/oauth/token?grant_type=client_credentials";
        String clientId = "48295f70b50f4aada2ea6c5ceefed4d6";
        String clientSecret = "M2GfXVEJ0CsZzAlnYpk9FHJuj5HuH9hah7dgXxctHbRfEUTUQ8Spq6eLZMpUovag";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> result = restTemplate.exchange(tokenUrl, HttpMethod.GET, entity, String.class);
        JSONObject jsonToken = new JSONObject(result.getBody());
        return jsonToken.getString("access_token");
    }

    public Offer getSearchedOfferFromAllegro(String offerId,
                                             String offerUrl,
                                             String searchedPhrase,
                                             String displayedName) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("ACCEPT", "application/vnd.allegro.public.v1+json");
        headers.setBearerAuth(this.getToken());
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "https://api.allegro.pl/offers/listing?phrase=" + searchedPhrase + "&searchMode=REGULAR",
                HttpMethod.GET,
                entity,
                String.class);
        JSONObject result = new JSONObject(response);
        JSONObject body = new JSONObject(result.getString("body"));
        JSONObject items = body.getJSONObject("items");
        JSONArray promoted = items.getJSONArray("promoted");
        JSONArray allOffers = items.getJSONArray("regular");

        for (int i = 0; i < promoted.length(); i++) {
            allOffers.put(promoted.getJSONObject(i));
        }
        JSONObject searchedOffer = null;
        for (int i = 0; i < allOffers.length(); i++) {
            if (allOffers.getJSONObject(i).get("id").equals(offerId)) {
                searchedOffer = allOffers.getJSONObject(i);
                break;
            }
        }
        if (searchedOffer == null) {
            return null;
        }

        String offerName = searchedOffer.getString("name");
        String offerPrice = searchedOffer.getJSONObject("sellingMode").getJSONObject("price").getString("amount");
        int offerAvailableUnits = searchedOffer.getJSONObject("stock").getInt("available");
        String categoryId = searchedOffer.getJSONObject("category").getString("id");

        return new Offer(Long.parseLong(offerId),
                offerUrl,
                offerName,
                displayedName,
                Double.parseDouble(offerPrice),
                offerAvailableUnits,
                new Category(Long.parseLong(categoryId)));
    }


}
