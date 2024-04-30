package com.coinconvert.models;

import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class ApiController {
    private static String apiKey="3d96049b8a15e50ab473bf23";
    
    public static String consultApi(String currency) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/"+currency))
                .build();

        HttpResponse<String> response=client
                .send(request,HttpResponse.BodyHandlers.ofString());

        return response.body();
        
    }

    public static double consultCurrency(String currency,String currency2){
        double usdConversionRate=0;
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + currency))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(json).getAsJsonObject();

            usdConversionRate = jsonObject.getAsJsonObject("conversion_rates").get(currency2).getAsDouble();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


       return  usdConversionRate;
    }
}
