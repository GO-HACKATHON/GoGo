package com.gogo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogo.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
public class QueryController {

    @RequestMapping(value = "/query" ,method = RequestMethod.GET, params = "message")
    public ResponseEntity<?> getUserByUsername(@RequestParam("message") String message) throws Exception {
        String apiai = sendGet(message);
        Message msg = new Message(apiai);
    }

    // HTTP GET request
    private String sendGet(String message) throws Exception {

        String url = "https://api.api.ai/v1/query?query="+ URLEncoder.encode(message,"UTF-8") +"&lang=en&sessionId=1732812321";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("Authorization", "Bearer a7d81f6de8154d748b48d9ae6913a517");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        return response.toString();
    }

    private String convertJSON(String jsonFormat) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonFormat);
        return rootNode.get("result").get("metadata").get("intentName").asText();
    }
}

