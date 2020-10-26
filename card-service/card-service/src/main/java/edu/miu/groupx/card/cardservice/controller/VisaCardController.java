package edu.miu.groupx.card.cardservice.controller;

import edu.miu.groupx.card.cardservice.model.MasterCard;
import edu.miu.groupx.card.cardservice.model.VisaCard;
import edu.miu.groupx.card.cardservice.service.VisaCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/visa-card")
public class VisaCardController {
    @Autowired
    private VisaCardService visaCardService;

    @GetMapping("/")
    public ResponseEntity<List<VisaCard>> getAllMasterCards() {

        return new ResponseEntity<List<VisaCard>>(visaCardService.findAllCards(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisaCard> getMasterCard(@PathVariable Long id) {
        return new ResponseEntity<VisaCard>(this.visaCardService.findCardById(id), HttpStatus.CREATED);

    }

    @PostMapping("")
    public ResponseEntity<VisaCard> createNewMasterCard(@RequestBody String holderName) {
        System.out.println("**********got a call from bank service **************");
        return new ResponseEntity<VisaCard>(this.visaCardService.createNewCardForCustomer(holderName), HttpStatus.CREATED);
    }

    //    @PutMapping("/")
//    public ResponseEntity<MasterCard> updateMasterCard(@RequestBody MasterCard card) {
//        return new ResponseEntity<MasterCard>(this.masterCardService.(card), HttpStatus.CREATED);
//    }
    @PostMapping("verify")
    public ResponseEntity<String> getMasterCardStatus(@RequestBody MasterCard card) {
        String holderName = card.getHolderName();
        String CCV = card.getCCV();
        return new ResponseEntity<String>(visaCardService.getVisaCardStatus(holderName, CCV), HttpStatus.CREATED);
    }
}
