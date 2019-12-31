package kurs.mapper;

import kurs.domain.Customer;
import kurs.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

Customer customerDtoToCustomer(CustomerDto customerDto);
CustomerDto customerToCustomerDto(Customer customer);
}
