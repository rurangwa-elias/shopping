package edu.miu.groupx.shop.shopping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShopController {
    @GetMapping("/hello")
    public ResponseEntity<String> getMessage(){
        return new ResponseEntity<String>("Hello here!", HttpStatus.FOUND);
    }
}
