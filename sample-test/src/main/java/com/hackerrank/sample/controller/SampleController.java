package com.hackerrank.sample.controller;

import com.hackerrank.sample.dto.StringResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SampleController {
    @GetMapping("/defaultHello")
    public StringResponse defaultHello(@RequestParam Optional<String> message){
        StringResponse response = new StringResponse(!message.isEmpty()?"Hello "+message.get():"Hello World!");
        return response;
    }

    @PostMapping("/customHello")
    public ResponseEntity customHello(@RequestParam String message){
        StringResponse response = null;
        if(message!=null && message.length()==0){
            return new ResponseEntity(new StringResponse("Custom "), HttpStatus.OK);
        }if(message!=null){
            return new ResponseEntity(new StringResponse("Custom "+message), HttpStatus.OK);
        }else{
            return new ResponseEntity(new StringResponse(message),HttpStatus.BAD_REQUEST);
        }
    }
}
