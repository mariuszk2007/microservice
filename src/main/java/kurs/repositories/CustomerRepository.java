package kurs.repositories;

import kurs.domain.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, UUID> {
}
