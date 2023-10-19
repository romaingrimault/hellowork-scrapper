package fr.romaingrimault.scrapper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Offer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "url")
    private String url;

    @Column(name = "ref")
    private String ref;

    @Column(name = "companyId", insertable = false, updatable = false)
    private String companyId;

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    public Offer() {
    }

    public Offer(String url, String ref, Company company) {
        super();
        this.url = url;
        this.ref = ref;        
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getCompanyId() {
        return companyId;
    }
}
