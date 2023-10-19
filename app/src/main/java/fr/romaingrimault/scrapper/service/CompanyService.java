package fr.romaingrimault.scrapper.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.romaingrimault.scrapper.model.Company;
import fr.romaingrimault.scrapper.repository.CompanyRepository;


@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;


    public Boolean isCompany(String name){
        if(companyRepository.findByCompanyName(name) == null){
            return false;
        }
        else{
            return true;
        }
    }

    public void addCompany(Company company){
        if(!this.isCompany(company.getCompanyName())){
            companyRepository.save(company);
        }
    }

    
    public List<Company> getAllCompanies(){
       List companyList = new ArrayList<>();
        companyRepository.findAll().forEach(company -> {
            companyList.add(company);
        });
        return companyList;
    }

    public Company getCompany(String companyName){
        return companyRepository.findByCompanyName(companyName);
    }
}
