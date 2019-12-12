package org.rastalion.brewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.rastalion.brewery.services.BeerService;
import org.rastalion.brewery.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDto validBeer;
    private String url;

    @BeforeEach
    void setUp() {
        validBeer = BeerDto.builder()
                .beerId(UUID.randomUUID())
                .beerName("Duvel")
                .beerStyle("Trappistje")
                .upc(123456789012L)
                .build();
        url = "/api/v1/beer/";
    }

    @Test
    void getBeer() throws Exception {
        given(beerService.getBeerById(any(UUID.class)))
                .willReturn(validBeer);

        mockMvc.perform(get(url + validBeer.getBeerId().toString())
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.beerId", is(validBeer.getBeerId().toString())))
                .andExpect(jsonPath("$.beerName", is(validBeer.getBeerName())));
    }

    @Test
    void handlePost() throws Exception {
        BeerDto beerDto = validBeer;
        beerDto.setBeerId(null);
        BeerDto savedDto = BeerDto.builder().beerId(UUID.randomUUID()).beerName("New Beer").build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewBeer(any())).willReturn(savedDto);

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void handleUpdate() throws Exception {
        // Given
        BeerDto beerDto = validBeer;
        beerDto.setBeerId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        // When
        mockMvc.perform(put(url + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());

        then(beerService)
                .should()
                .updateBeer(any(), any());
    }

    @AfterEach
    void tearDown() {
        validBeer = null;
        url = null;
    }
}