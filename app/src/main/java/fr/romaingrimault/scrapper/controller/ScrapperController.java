package fr.romaingrimault.scrapper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.romaingrimault.scrapper.service.ScrapperService;

@RestController
@RequestMapping("/scrapper")
public class ScrapperController {
    
    @Autowired
    private ScrapperService scrapperService;

    /**
     * @param url
     * @throws Exception
     */
    @PostMapping("/")
    public void scrapUrl(@RequestBody String url) throws Exception{
        this.scrapperService.scrapUrl(url);
    }
}
