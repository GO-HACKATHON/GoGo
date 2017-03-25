package com.gogo.controller;

import com.gogo.model.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {

    @RequestMapping(value = "/query" ,method = RequestMethod.GET, params = "message")
    public ResponseEntity<?> getUserByUsername(@RequestParam("message") String message) {
        Message msg = new Message(message);
        return new ResponseEntity(msg, HttpStatus.OK);
    }

}

