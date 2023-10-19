package fr.romaingrimault.scrapper.model;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String companyId;

    @Column(name = "companyName")
    private String companyName;

    @OneToMany(mappedBy = "company")
    private Set<Offer> offers;

    public Set<Offer> getOffers() {
        return offers;
    }


    public Company() {
    }

    public Company(String companyName) {
        super();
        this.companyName = companyName;
    }
    
    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public String getId() {
        return companyId;
    }

    public void setId(String id) {
        this.companyId = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Boolean isOffer(Offer offer){
        if(offers != null){
            return offers.contains(offer);
        }
        else{
            return false;
        }
    }
}