package kurs.mapper;

import jdk.jfr.Category;
import kurs.domain.Customer;
import kurs.web.model.CustomerDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper( componentModel = "spring")
public interface CustomerMapper {

Customer customerDtoToCustomer(CustomerDto customerDto);
CustomerDto customerToCustomerDto(Customer customer);
}
