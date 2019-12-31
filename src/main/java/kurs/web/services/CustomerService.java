package kurs.web.services;

import kurs.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {

    CustomerDto getCustmerById(UUID id);

    CustomerDto saveCustomer(CustomerDto customerDto);

    void updateCustomer(UUID customerId, CustomerDto customerDto);

    void deleteCustomer(UUID customerId);
}
