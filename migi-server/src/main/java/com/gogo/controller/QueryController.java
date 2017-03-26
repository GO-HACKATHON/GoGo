package com.gogo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogo.algorithm.TrajectoryPlanner;
import com.gogo.model.Message;
import com.gogo.model.Response;
import com.gogo.model.Bus;
import com.mongodb.MongoClient;
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
    public ResponseEntity<?> relayMessage(@RequestParam("message") String message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        MongoClient mongoClient = new MongoClient();
        String responseMessage;

        String apiai = sendGet(message);
        Message msg = new Message(apiai);

        String intent = getIntent(objectMapper, msg.getMessage());
        String addressTo = getAddress(objectMapper, msg.getMessage(), "addressTo");
        String addressFrom = getAddress(objectMapper, msg.getMessage(), "addressFrom");

        if (intent.equals("location.get")) {
            TrajectoryPlanner trajactoryPlanner = new TrajectoryPlanner(mongoClient);
            Response response = new Response();
            response = trajactoryPlanner.getLocation(addressFrom, addressTo);
            //get location method
        }
        else {
            responseMessage = this.convertToJSON("I beg your pardon. Can you ask another question?");
        }

        return new ResponseEntity(responseMessage, HttpStatus.OK);
    }

    private String buildResponse(Response response) {
        //String[] responseGetStopBus = new String[10];
        String[] responseTrajectory = new String[10];
        //String[] responseArrivalTime = new String[10];

        responseTrajectory[0] = "If you want to go to" + response.getAddressTo() + "from" + response.getAddressFrom() +
                "you can take this route: " + response.getBusTrajectory() + "Be careful, your beloved waiting for you. If you have one btw :p";
        responseTrajectory[1] = "The shortest route from" + response.getAddressFrom() + "to" + response.getAddressTo() + "is by" +
                response.getBusTrajectory();
        responseTrajectory[2] = "You can reach" + response.getAddressTo() + "from" + response.getAddressFrom() + "by this route:" +
                response.getBusTrajectory();
        responseTrajectory[3] = "The best route from" + response.getAddressFrom() + "to" + response.getAddressTo() + "is by" +
                response.getBusTrajectory();
        responseTrajectory[4] = "You can take this route: " + response.getBusTrajectory() + "Thanks for not causing traffic jam by using transjogja." +
                "\nGood job!";
        responseTrajectory[5] = "You can reach that place by" + response.getBusTrajectory() + "\nDont't forget bring what you need";
        //responseTrajectory[6] = ""

//        responseGetStopBus[0] = "The nearest stop bus is at" + response.getNearestBusStop() + "Please be careful to get there :D";
//        responseGetStopBus[1] = "You can go to" + response.getNearestBusStop() + ", that is the nearest to you";
//        responseGetStopBus[2] = "You can reach" + response.getNearestBusStop() + "by walking. So you will be healthier :)";
//        responseGetStopBus[3] = "I suggest you to go to " + response.getNearestBusStop() + ", but It's up to you :D";
//        responseGetStopBus[4] = "You can go to" + response.getNearestBusStop() +". You're awesome, because you use transjogja.";
//        responseGetStopBus[5] = "Please go to" + response.getNearestBusStop() + ". Thank you for making our world to be a better place to live by riding public transportation";
//
//        responseArrivalTime[0] = "Your bus will be arrived in" + response.getArrivalTime();
//        responseArrivalTime[1] = "Your bus will be arrived in" + response.getArrivalTime() + "You have to hurry or you can wait for the next bus." +
//                "Will come in" + response.getArrivalTimeAlternative() + "btw :D";
//        responseArrivalTime[2] = "Will come in" + response.getArrivalTime() + "You can do what you want now, because the bus still far away :)";
//        responseArrivalTime[3] = "Thank you for being patience. Your bus will come in" + response.getArrivalTime();
//        responseArrivalTime[4] = "Take your time! You still have long time to wait. The bus will arrived at" + response.getArrivalTime();


        return this.convertToJSON();
    }

    // HTTP GET request
    private String sendGet( String message) throws Exception {
        String api_ai_token = "bearer a7d81f6de8154d748b48d9ae6913a517";
        String url = "https://api.api.ai/v1/query?query="+ URLEncoder.encode(message,"UTF-8") +"&lang=en&sessionId=1732812321";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("Authorization", api_ai_token);

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

    private String getIntent(ObjectMapper mapper, String jsonFormat) throws IOException {
        JsonNode rootNode = mapper.readTree(jsonFormat);
        return rootNode.get("result").get("metadata").get("intentName").asText();
    }

    private String getAddress(ObjectMapper mapper, String jsonFormat, String paramater) throws IOException {
        JsonNode rootNode = mapper.readTree(jsonFormat);
        return rootNode.get("result").get("parameters").get(paramater).asText();
    }

    private String convertToJSON(String message) {
        return "{\"message\": \"" + message +"\"";
    }
}

