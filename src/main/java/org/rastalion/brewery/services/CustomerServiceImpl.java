package org.rastalion.brewery.services;

import lombok.extern.log4j.Log4j2;
import org.rastalion.brewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        log.info("Get customer by id: {}", customerId);
        return CustomerDto.builder()
                .customerId(UUID.randomUUID())
                .customerName("Alex")
                .build();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        log.info("Saved: {}", customerDto);

        return CustomerDto.builder()
                .customerId(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        log.info("Change requested: {}", customerDto);
    }

    @Override
    public void deleteById(UUID customerId) {
        log.info("Request for deleting a customer with id: {}", customerId);
    }


}
