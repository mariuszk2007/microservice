package kurs.web.services;

import kurs.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustmerById(UUID id) {
        return CustomerDto.builder()
                .customerId(UUID.randomUUID())
                .customerName("Mariusz Kukulski")
                .build();
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        return CustomerDto.builder()
                .customerId(UUID.randomUUID())
                .customerName("Tymon Kukulski")
                .build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        log.debug("Customer " + customerId +" updated");
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        log.debug("Customer " + customerId +" deleted");
    }
}
