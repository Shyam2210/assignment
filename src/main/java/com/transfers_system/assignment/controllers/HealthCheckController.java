package com.transfers_system.assignment.controllers;
/*
 * Created by: Shyam Gupta
 * Date: 01/05/25
 * Project: assignment
 */


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping(path = "/assignment/healthcheck")
    public ResponseEntity healthCheck(){
        return new ResponseEntity("Up and Running", HttpStatus.OK);
    }
}
