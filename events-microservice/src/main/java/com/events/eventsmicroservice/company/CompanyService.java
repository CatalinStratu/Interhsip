package com.events.eventsmicroservice.company;

import com.events.eventsmicroservice.candidate.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, ModelMapper modelMapper) {

        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
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
     * @param id company event ID
     * @param company The company model
     * @return Details of the company event
     */
    public HttpStatus updateCompanyEvent(@NotNull String id, @NotNull Company company) {
        Optional<Company> companyData = companyRepository.findById(id);

        return companyData.map(result -> {
            Company toBeUpdate =  companyData.get();
            toBeUpdate.setCompanyId(company.getCompanyId());
            toBeUpdate.setMessage(company.getMessage());
            companyRepository.save(toBeUpdate);
            return HttpStatus.OK;
        }).orElse(HttpStatus.NOT_FOUND);
    }

    /**
     * Return company event
     *
     * @param id The company model
     * @return Details of the company event
     */
    public Optional<CompanyDTO> getCompanyEvent(@NotNull String id) {
        return companyRepository
                .findById(id)
                .map(result -> modelMapper.map(result,CompanyDTO.class));
    }

    /**
     * Return all company events
     *
     * @param id The company model
     * @return Details of the company event
     */
    public List<CompanyDTO> getAllEventsByCompany(@NotNull String id){
        return companyRepository
                .findAllByCompanyId(id)
                .stream()
                .map(result -> modelMapper.map(result,CompanyDTO.class))
                .collect(Collectors.toList());
    }
}
