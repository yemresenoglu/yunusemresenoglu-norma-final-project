package com.example.finalproject.controller;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;


@RestController
@RequiredArgsConstructor
public class CurrencyExternalAPIController {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    @GetMapping
    public BigDecimal getCurrency(String fromCurrency, String toCurrency) throws IOException {


        BigDecimal currency = null;

        String apiUrl = "https://api.fastforex.io/fetch-one?from=" + fromCurrency + "&to=" + toCurrency + "&api_key=cda2fab47a-f88e5c2128-rd5cmu";
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);

        JSONParser jsonParser = new JSONParser(response.getBody());
        //Read JSON file
        Object obj = jsonParser;

        JSONArray dataList = (JSONArray) obj;

        //Iterate over currency data array
        for (int i = 0; i < dataList.size(); i++) {
            JSONObject currencyData = (JSONObject) dataList.get(i);

            currency = (BigDecimal) currencyData.get(toCurrency);
        }

        return currency;

    }


}
