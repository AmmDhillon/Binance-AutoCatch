package com.ammdhillon.autocatch.model.entity_model.swap_transaction;

import com.ammdhillon.autocatch.model.enums.ContractCurrency;
import com.ammdhillon.autocatch.model.enums.ContractType;
import com.ammdhillon.autocatch.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "swap_transaction")
public class SwapTransaction {

    @Id
    @SequenceGenerator(sequenceName = "swap_transaction_sequence", allocationSize = 1, name = "swap_transaction_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "swap_transaction_sequence")
    private Long id;
    private String hash;
    private Double amount;
    private String toAddress;
    private String fromAddress;
    private String contract;
    private Boolean test;
    @Enumerated(EnumType.STRING)
    private ContractType contractType;
    @Enumerated(EnumType.STRING)
    private ContractCurrency contractCurrency;
    private String remark;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
