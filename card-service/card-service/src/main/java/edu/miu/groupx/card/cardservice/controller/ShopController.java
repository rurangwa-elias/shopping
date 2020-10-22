package edu.miu.groupx.card.cardservice.controller;

import edu.miu.groupx.card.cardservice.model.Card;
import edu.miu.groupx.card.cardservice.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/shopservice/cards")
public class ShopController {
    @Autowired
    private CardService cardService;

//    public ResponseEntity<Card>getAllCards(){
//
//    }
}
