package com.gogo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryController {

    @RequestMapping(value = "/query" ,method = RequestMethod.GET, params = "username")
    public ResponseEntity<?> getUserByUsername(@RequestParam("username") String username) {
        return new ResponseEntity("Hi " + username, HttpStatus.OK);
    }

}

