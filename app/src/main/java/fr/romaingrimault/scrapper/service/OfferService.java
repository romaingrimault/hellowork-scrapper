package fr.romaingrimault.scrapper.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.romaingrimault.scrapper.model.Offer;
import fr.romaingrimault.scrapper.repository.OfferRepository;

@Service
public class OfferService {
    
    @Autowired
    private OfferRepository offerRepository;

    public Offer getOffer(String ref){

        return offerRepository.findByRef(ref);
    }

    public Boolean isOffer(String ref){
        if(offerRepository.findByRef(ref) == null){
            return false;
        }
        else{
            return true;
        }
    }

    public String getUrl(String ref){
        if(isOffer(ref)){
            Offer offer = offerRepository.findByRef(ref);
            return offer.getUrl();
        }
        else{
            return "Pas d'offre trouvée avec la référence " + ref;
        }
        
    }

    public void addOffer(Offer offer){
        if(!this.isOffer(offer.getRef())){
            offerRepository.save(offer);
        }
    }

    public List<Offer> getAllOffers(){
       List offerList = new ArrayList<>();
        offerRepository.findAll().forEach(offer -> {
            offerList.add(offer);
        });
        return offerList;
    }
}
