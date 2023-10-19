package fr.romaingrimault.scrapper.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import fr.romaingrimault.scrapper.model.Company;
import fr.romaingrimault.scrapper.model.Offer;


@Service
public class ScrapperService {

    private final WebClient webClient = new WebClient(BrowserVersion.CHROME);;

    @Autowired
    private OfferService offerService;

    @Autowired
    private CompanyService companyService;
    /**
     * Parse les offres d'emploi pour les sauvegardées en base de données
     * @param url
     * @throws Exception
     */
    public void scrapUrl(String url) throws Exception{
        this.webClient.getOptions().setCssEnabled(false);
        this.webClient.getOptions().setJavaScriptEnabled(false);
        url = url.replace("\"", "");
        try{
            HtmlPage page = this.webClient.getPage(url);
            Company company = this.getCompanyName(page);
            if(company.getCompanyName() != ""){
                ArrayList<String> offersUrl = this.getOfferUrl(page);
                offersUrl.forEach( offerUrl -> {
                    try {
                        String ref = this.getRef(this.webClient.getPage(offerUrl));
                        Offer tmpOffer = offerService.getOffer(ref);
                        if(!ref.isEmpty() && !company.isOffer(tmpOffer)){
                            System.out.println("create offer wth ref "+ref);
                            Offer offer = new Offer(offerUrl, ref,company);
                            offerService.addOffer(offer);
                        }
                        else{
                            System.out.println("ref not created");
                        }
                        
                    } catch (Exception e) {
                    System.out.println(e);
                    }
                });
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Parse les offres d'emploi de la page pour en générer les urls
     * @param page
     * @return
     */
    private ArrayList<String> getOfferUrl(HtmlPage page){
        List<DomNode> offersList = page.querySelectorAll("[data-offer-extract]");   
        ArrayList<String> offresId = new ArrayList<>();
        offersList.forEach((offer)->{
            if(offer.getAttributes().getNamedItem("id") != null){
                String str = offer.getAttributes().getNamedItem("id").getNodeValue();
                offresId.add(str);
            } 
        });
        ArrayList<String> offersUrl = new ArrayList<>();
        offresId.forEach(id->{
            String url = "https://www.hellowork.com/fr-fr/emplois/"+ id  +".html";
            offersUrl.add(url);
        });
        return offersUrl;
    }


    /**
     * Extraction de la référence de l'offre
     * @param page
     * @return
     */
    private String getRef(HtmlPage page){
        String ref = "";
        String xPath = "//div[@class='tw-layout-inner-grid']/div[last()]//section/span[last()]";
        List<DomNode> offersList = (List<DomNode>) page.getByXPath(xPath);
        if(offersList.size() == 1){
            String htmlText = offersList.get(0).asText();
            String[] parties = htmlText.split("Réf : ") ;
            if (parties.length > 1) {
                ref = parties[1];
            }
        }
        return ref;
    }


    private Company getCompanyName(HtmlPage page){
        Company company = new Company();
        String xPath = "//h2/span[@class='strong']";
        DomNode node =  (DomNode) page.getByXPath(xPath).get(0);
        if(node != null){
            String companyName = node.asText().trim();
            if(companyService.isCompany(companyName)){
                company = companyService.getCompany(companyName);
            }
            else{
                company = new Company(companyName);
                companyService.addCompany(company);
            }
        }
        return company;
    }
}
