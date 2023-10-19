package fr.romaingrimault.scrapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.romaingrimault.scrapper.service.ScrapperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/scrapper")
public class ScrapperController {
    
    @Autowired
    private ScrapperService scrapperService;

    /**
     * Prends en paramètre l'URL d'une page d'offres d'emploi d'entreprise du site Hellowork pour les analyser. 
     * @param url
     * @throws Exception
     */
    @Operation(summary ="Parse l'ensemble des offres d'une entreprise sur le site hellowork en fonction de l'url fournit")
    @PostMapping("/")
    public void scrapUrl(@Parameter(description = "url contenant la page d'offre d'une entreprise", required = true) @RequestParam  String url) throws Exception{
        this.scrapperService.scrapUrl(url);
    }
}
