package org.rastalion.brewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.rastalion.brewery.services.CustomerService;
import org.rastalion.brewery.web.model.CustomerDto;
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
@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CustomerDto validCustomer;
    String url;

    @BeforeEach
    void setUp() {
        validCustomer = CustomerDto.builder()
                .customerId(UUID.randomUUID())
                .customerName("Alex")
                .build();
        url = "/api/v1/customer/";
    }

    @Test
    void getCustomer() throws Exception {
        given(customerService.getCustomerById(any(UUID.class))).willReturn(validCustomer);

        mockMvc.perform(get(url + validCustomer.getCustomerId().toString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerId", is(validCustomer.getCustomerId().toString())))
                .andExpect(jsonPath("$.customerName", is(validCustomer.getCustomerName())));

    }

    @Test
    void handlePost() throws Exception {
        CustomerDto customerDto = validCustomer;
        customerDto.setCustomerId(null);
        CustomerDto savedDto = CustomerDto.builder().customerId(UUID.randomUUID()).customerName("Eliot").build();
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        given(customerService.saveNewCustomer(any())).willReturn(savedDto);

        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void handleUpdate() throws Exception {
        CustomerDto customerDto = validCustomer;
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        mockMvc.perform(put(url + validCustomer.getCustomerId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(customerDtoJson))
                .andExpect(status().isNoContent());

        then(customerService).should()
                .updateCustomer(any(), any());
    }

    @AfterEach
    void tearDown() {
        validCustomer = null;
        url = null;
    }
}