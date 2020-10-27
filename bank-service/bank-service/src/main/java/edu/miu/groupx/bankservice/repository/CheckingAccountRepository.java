package edu.miu.groupx.bankservice.repository;

import edu.miu.groupx.bankservice.model.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {
//
//    @Query(value ="FROM CheckingAccount acc where acc.accountNumber =?1")
    public CheckingAccount findCheckingAccountByAccountNumber(String accountNumber);

}
