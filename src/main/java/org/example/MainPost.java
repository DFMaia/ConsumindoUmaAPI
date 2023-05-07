package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.json.JSONObject;


//Link do YouTube ( https://www.youtube.com/watch?v=PZEUZ0pY6ws )

public class MainPost {

    public static void main(String[] args)throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString("{\"canal\": \"RinaldoDev\"}")) // Mandando um corpo como POST
                .uri(URI.create("https://postman-echo.com/post"))
                .timeout(Duration.ofSeconds(3))
                .build();

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
        System.out.println(response.statusCode());
        System.out.println(response.headers());
        System.out.println(response.version());

        //Consumindo uma info específica do JSON.
        JSONObject jsonObject = new JSONObject(response.body());
        String xAmznTraceId = jsonObject.getJSONObject("headers").getString("x-amzn-trace-id");
        System.out.println("ISSO AQUI SOU EU PEGANDO UMA INFO QUE EU QUERO DENTRO DO JSON: " + xAmznTraceId);



    }

}
