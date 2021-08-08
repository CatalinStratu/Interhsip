package com.events.eventsmicroservice.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping(path = "add")
    public ResponseEntity<Company> addCompanyEvent(Company company){
        return ResponseEntity.ok(companyService.addCompanyEvent(company));
    }

    @PutMapping(path = "add/{id}")
    public ResponseEntity<HttpStatus> updateCompanyEvent(@PathVariable String id, Company company){
        return ResponseEntity.ok(companyService.updateCompanyEvent(id, company));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Company> getCompanyEvent(@PathVariable String id) {
        return ResponseEntity.ok(companyService.getCompanyEvent(id));
    }

}