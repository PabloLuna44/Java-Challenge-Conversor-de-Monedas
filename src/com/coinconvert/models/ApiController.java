package com.coinconvert;

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

    public static String convertCurrency(String currency,String currency2) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/"+apiKey+"/latest/"+currency))
                .build();

        HttpResponse<String> response=client
                .send(request,HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}
