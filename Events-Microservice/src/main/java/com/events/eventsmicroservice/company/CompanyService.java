package com.events.eventsmicroservice.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(final CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Add company Event
     *
     * @param company The company model
     * @return Details of the company event
     */
    public Company addCompanyEvent(@NotNull Company company) {
        return companyRepository.save(company);
    }

    /**
     * Update company Event
     *
     * @param company The company model
     * @return Details of the company event
     */
    public HttpStatus updateCompanyEvent(@NotNull String id, @NotNull Company company) {
        Optional<Company> companyData = companyRepository.findById(id);

        if(companyData.isPresent()){
            Company toBeUpdate =  companyData.get();
            toBeUpdate.setCompanyId(company.getCompanyId());
            toBeUpdate.setMessage(company.getMessage());
            companyRepository.save(toBeUpdate);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * Add company Event
     *
     * @param id The company model
     * @return Details of the company event
     */
    public Company getCompanyEvent(@NotNull String id) {
        return companyRepository.findById(id).get();
    }

    public List<Company> getAllEventsByCompany(@NotNull String id){
        return companyRepository.findAllByCompanyId(id);
    }

}
