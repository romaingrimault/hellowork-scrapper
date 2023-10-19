package fr.romaingrimault.scrapper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.romaingrimault.scrapper.model.Offer;
import fr.romaingrimault.scrapper.service.OfferService;

@RestController
@RequestMapping("/offer")
public class OfferController {
    
    @Autowired
    private OfferService offerService;


    @GetMapping("/{ref}")
    public Offer getOffer(@PathVariable("ref") String ref){
        return offerService.getOffer(ref);
    }

    @GetMapping("/getUrl/{ref}")
    public String getUrl(@PathVariable("ref") String ref){
        return offerService.getUrl(ref);
    }
    @GetMapping("/getAll")
    public List<Offer> getAll(){
        return offerService.getAllOffers();
    }
}
