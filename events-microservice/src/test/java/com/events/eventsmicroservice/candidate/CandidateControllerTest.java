package com.events.eventsmicroservice.candidate;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
public class CandidateControllerTest {

    @Mock
    private CandidateService candidateService;

    @InjectMocks
    private CandidateController candidateController;

    private MockMvc mockMvc;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(candidateController).build();
    }

    @Test
    @DisplayName("Create a new candidate entity")
    @SneakyThrows
    void testCreate(){
        CandidateDTO dto = new CandidateDTO();
        Candidate candidate = new Candidate();
        MockHttpServletRequestBuilder request = post("/api/v1/candidates/add")
                .content(objectMapper.writeValueAsString(candidate))
                .contentType(MediaType.APPLICATION_JSON_VALUE);
        when(candidateService.addCandidateEvent(any())).thenReturn(candidate);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(dto.getId())))
                .andExpect(jsonPath("$.candidateId", is(dto.getCandidateId())))
                .andExpect(jsonPath("$.message", is(dto.getMessage())));
    }

    @Nested
    @DisplayName("Get all candidates entities")
    class testGetAll {

        @Test
        @DisplayName("Get all candidate by ID")
        @SneakyThrows
        void testGetById() {
            CandidateDTO dto = new CandidateDTO();
            MockHttpServletRequestBuilder request = get("/api/v1/candidates/1")
                    .contentType(MediaType.APPLICATION_JSON_VALUE);
            when(candidateService.getCandidateEvent(anyString())).thenReturn(Optional.of(dto));

            mockMvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(dto.getId())))
                    .andExpect(jsonPath("$.candidateId", is(dto.getCandidateId())))
                    .andExpect(jsonPath("$.message", is(dto.getMessage())));
        }
    }
}
