package fr.romaingrimault.scrapper.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


@Service
public class ScrapperService {

    private final WebClient webClient = new WebClient(BrowserVersion.CHROME);;

    public void scrapUrl(String url) throws Exception{
        this.webClient.getOptions().setCssEnabled(false);
        this.webClient.getOptions().setJavaScriptEnabled(false);
        url = url.replace("\"", "");
        try{
            HtmlPage page = this.webClient.getPage(url);
            ArrayList<String> offersUrl = this.getOfferUrl(page);
            offersUrl.forEach( offerUrl -> {
                System.out.println("url: "+offerUrl);

                try {

                    String ref = this.getRef(this.webClient.getPage(offerUrl));
                    System.out.println("Ref: "+ref);
                    System.out.println("\n--------\n");
                    // insert to bdd

                } catch (Exception e) {
                  System.out.println(e);
                }
            });
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

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


    private String getRef(HtmlPage page){
        String ref = "";
            String xPath = "//div[@class='tw-col-span-full lg:tw-col-span-4 md:tw-mb-12']//section/span[last()]";
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
}
