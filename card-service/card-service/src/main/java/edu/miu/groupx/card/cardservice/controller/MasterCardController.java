package edu.miu.groupx.card.cardservice.controller;

import edu.miu.groupx.card.cardservice.service.VisaCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/shopservice/cards")
public class ShopController {
    @Autowired
    private VisaCardService visaCardService;

//    public ResponseEntity<Card>getAllCards(){
//
//    }
}
