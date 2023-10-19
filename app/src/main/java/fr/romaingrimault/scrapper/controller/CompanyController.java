package fr.romaingrimault.scrapper.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.romaingrimault.scrapper.model.Company;
import fr.romaingrimault.scrapper.service.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
    
    @Autowired
    private CompanyService companyService;

    @GetMapping("/{name}")
    public Company getCompany(@PathVariable("name") String name){
        return companyService.getCompany(name);
    }   

    @GetMapping("/all")
    public List<Company> getAllCompany(){
        return companyService.getAllCompanies();
    }   
}
