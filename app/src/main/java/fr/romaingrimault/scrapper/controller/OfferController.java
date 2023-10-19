package fr.romaingrimault.scrapper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.romaingrimault.scrapper.model.Offer;
import fr.romaingrimault.scrapper.service.OfferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/offer")
public class OfferController {
    
    @Autowired
    private OfferService offerService;

    /**
     * @param ref prend la ref d'une offre  
     * @return retourne l'offre
     */
    @Operation(summary ="Retourne une offre à partir de sa reférence")
    @GetMapping("/")
    public Offer getOffer(@Parameter(description = "Reférence de l'offre") @RequestParam String ref){
        return offerService.getOffer(ref);
    }

    /**
     * @param ref prend la ref d'une offre 
     * @return retourne l'url de l'offre
     */
    @Operation(summary ="Retourne l'url de l'offre à partir de sa reférence")
    @GetMapping("/getUrl")
    public String getUrl(@Parameter(description = "Reférence de l'offre") @RequestParam String ref){
        return offerService.getUrl(ref);
    }

    @Operation(summary ="Retourne l'enssemble des offres")
    @GetMapping("/all")
    public List<Offer> getAll(){
        return offerService.getAllOffers();
    }
}
