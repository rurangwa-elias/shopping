package edu.miu.groupx.bankservice.repository;

import edu.miu.groupx.bankservice.model.CheckingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CheckingAccountRepository extends JpaRepository<CheckingAccount, Long> {
    public CheckingAccount findCheckingAccountByCardCardNumber(String cardNumber);
}
