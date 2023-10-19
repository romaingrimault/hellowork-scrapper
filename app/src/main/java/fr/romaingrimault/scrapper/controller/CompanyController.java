package fr.romaingrimault.scrapper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.romaingrimault.scrapper.model.Company;
import fr.romaingrimault.scrapper.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/company")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;

    @Operation(summary ="Retourne une entreprise ainsi que ses offres d'emploi déjà sauvegardé")
    @GetMapping("/")
    public Company getCompany(@Parameter(description = "Nom de l'entreprise") @RequestParam String name){
        return companyService.getCompany(name);
    }   
    
    @Operation(summary ="Retourne l'ensemble des entreprises et leurs offres")
    @GetMapping("/all")
    public List<Company> getAllCompany(){
        return companyService.getAllCompanies();
    }   
}
