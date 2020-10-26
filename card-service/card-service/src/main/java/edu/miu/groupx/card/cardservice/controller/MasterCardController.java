package edu.miu.groupx.card.cardservice.controller;

import edu.miu.groupx.card.cardservice.model.MasterCard;
import edu.miu.groupx.card.cardservice.service.MasterCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/master-card")
public class MasterCardController {
    @Autowired
    private MasterCardService masterCardService;

    @GetMapping("/")
    public ResponseEntity<List<MasterCard>> getAllMasterCards() {

        return new ResponseEntity<List<MasterCard>>(masterCardService.findAllCards(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MasterCard> getMasterCard(@PathVariable Long id) {
        return new ResponseEntity<MasterCard>(this.masterCardService.findCardById(id), HttpStatus.CREATED);

    }

    @PostMapping("")
    public ResponseEntity<MasterCard> createNewMasterCard(@RequestBody String holderName) {
        System.out.println("**********got a call from bank service **************");
        return new ResponseEntity<MasterCard>(this.masterCardService.createNewCardForCustomer(holderName), HttpStatus.CREATED);
    }

//    @PutMapping("/")
//    public ResponseEntity<MasterCard> updateMasterCard(@RequestBody MasterCard card) {
//        return new ResponseEntity<MasterCard>(this.masterCardService.(card), HttpStatus.CREATED);
//    }
    @PostMapping("verify")
    public ResponseEntity<String> getMasterCardStatus(@RequestBody MasterCard card){
        String holderName = card.getHolderName();
        String CCV = card.getCCV();
        return new ResponseEntity<String>(masterCardService.getMasterCardStatus(holderName, CCV), HttpStatus.CREATED);
    }

}
