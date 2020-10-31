package edu.miu.groupx.bankservice.repository;

import edu.miu.groupx.bankservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
