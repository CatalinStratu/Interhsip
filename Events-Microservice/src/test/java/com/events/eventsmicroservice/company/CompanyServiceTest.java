package com.events.eventsmicroservice.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    CompanyRepository repository;
    @Mock
    ModelMapper mapper;

    CompanyService service;

    @BeforeEach
    void setUp() {
        repository = mock(CompanyRepository.class);
        mapper = mock(ModelMapper.class);
        service = new CompanyService(repository, mapper);
    }

    @Test
    @DisplayName("Create company event")
    public void testCreateCompany_thenReturn_Candidate(){
        var companyOk = Optional.of(new Company("ID","TEST MESSAGE"));
        when(repository.save(any(Company.class))).thenReturn(companyOk.get());
        Company company = service.addCompanyEvent(companyOk.get());

        assertThat(company).isEqualTo(companyOk.get());
    }

    @Test
    @DisplayName("Get All Events By Company")
    public void testGetAllEventsByCompany_thenReturn_ListOfCompanyDTO(){

        CompanyDTO dto = mock(CompanyDTO.class);
        Company company = mock(Company.class);

        List<Company> companies = Collections.nCopies(3, company);

        when(repository.findAllByCompanyId(any())).thenReturn(companies);
        when(mapper.map(company, CompanyDTO.class)).thenReturn(dto);

        List<CompanyDTO> companyDTOS = service.getAllEventsByCompany(any());

        assertThat(companies.size()).isEqualTo(companyDTOS.size());
    }

    @Test
    @DisplayName("Get event by company ID")
    public void testGetCompanyEvent_thenReturn_CandidateDTO(){

        CompanyDTO dto = mock(CompanyDTO.class);
        var companyOk = Optional.of(new Company("ID","TEST MESSAGE"));

        when(repository.findById(any())).thenReturn(companyOk);
        when(mapper.map(companyOk.get(), CompanyDTO.class)).thenReturn(dto);

        CompanyDTO companyDTO = service.getCompanyEvent(any(String.class));

        assertThat(dto).isEqualTo(companyDTO);
    }

    @Test
    @DisplayName("Update company information when job exist")
    public void testUpdateWhenCompanyExists_thenReturn_OK() {
        // Given
        var companyOk = Optional.of(new Company("ID", "MESSAGE"));
        when(repository.findById(any(String.class))).thenReturn(companyOk);
        // When
        HttpStatus status = service.updateCompanyEvent("ID", companyOk.get());
        // Then
        assertThat(status).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Update company information when job not exist")
    public void testUpdateWhenCompanyDoesNotExists_thenNOT_FOUND() {
        // Given
        var companyOk = Optional.of(new Company("ID", "MESSAGE"));
        when(repository.findById(any(String.class))).thenReturn(Optional.empty());
        // When
        HttpStatus status = service.updateCompanyEvent("ID", companyOk.get());
        // Then
        assertThat(status).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
