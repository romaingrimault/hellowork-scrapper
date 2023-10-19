package fr.romaingrimault.scrapper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.romaingrimault.scrapper.model.Offer;


@Repository
public interface OfferRepository extends CrudRepository <Offer, String> {
    
    Offer findByRef(String ref);
}
