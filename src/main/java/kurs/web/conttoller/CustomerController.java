package kurs.web.conttoller;

import kurs.domain.Customer;
import kurs.mapper.CustomerMapper;
import kurs.repositories.CustomerRepository;
import kurs.web.model.CustomerDto;
import kurs.web.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerMapper customerMapper;


    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId){


      //  return new ResponseEntity<>(customerService.getCustmerById(customerId),HttpStatus.OK);
        return new ResponseEntity(customerRepository.findById(customerId).get(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity handlePostCustomer(@Valid @RequestBody CustomerDto customerDto){
       // CustomerDto savedCustomer = customerService.saveCustomer(customerDto);
        Customer customer = customerRepository.save(customerMapper.customerDtoToCustomer(customerDto));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location" ,"/api/v1/customer/"+ customer.getCustomerId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity handlePutCustomer(@PathVariable("customerId") UUID customerId,@Valid @RequestBody CustomerDto customerDto){
       customerRepository.findById(customerId).ifPresent(customer -> {
           customer.setCustomerName(customerDto.getCustomerName());
           customerRepository.save(customer);
       });

       // customerService.updateCustomer(customerId, customerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDeleteCustomer(@PathVariable("customerId") UUID customerId){
        customerRepository.findById(customerId).ifPresent(customer -> {

            customerRepository.delete(customer);
        });

       // customerService.deleteCustomer(customerId);
    }
 }
