package edu.miu.groupx.bankservice.model;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class CheckingAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Card card;
    @OneToMany
    private List<AccountEntries> accountEntries = new ArrayList<>();
    private BigDecimal balance;

}
