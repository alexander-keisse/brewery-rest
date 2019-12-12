package org.rastalion.brewery.web.mappers;

import org.mapstruct.Mapper;
import org.rastalion.brewery.domain.Customer;
import org.rastalion.brewery.web.model.CustomerDto;

/*
This is to convert between our domain layer and the objects from our web layer

also take a look at: target/classes/org/rastalion/brewery/web/mappers
 */

@Mapper
public interface CustomerMapper {

    CustomerDto customerToCustomerDto(Customer customer);

    Customer customerDtoToCustomer(CustomerDto dto);

}
