package fr.romaingrimault.scrapper.repository;

import org.springframework.data.repository.CrudRepository;

import fr.romaingrimault.scrapper.model.Company;

public interface CompanyRepository extends CrudRepository<Company , String> {
    Company findByCompanyName(String companyName);
}
