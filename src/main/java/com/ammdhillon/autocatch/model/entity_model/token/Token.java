package com.ammdhillon.autocatch.model.entity_model.token;

import com.ammdhillon.autocatch.model.enums.ContractCurrency;
import com.ammdhillon.autocatch.model.enums.ContractType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "token")
@IdClass(TokenID.class)
public class Token {

    @Id
    @SequenceGenerator(sequenceName = "token_sequence", allocationSize = 1, name = "token_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
    private Long id;
    @Id
    @Column(unique = true)
    private String symbol = "";
    private String name = "";
    private Long binanceId = 0L;
    private String binanceCode = "";
    private Long releaseData = 0L;
    private Double amount = 0.0;
    private String contract = "";
    @Enumerated(EnumType.STRING)
    private ContractType contractType = ContractType.NULL;
    @Enumerated(EnumType.STRING)
    private ContractCurrency contractCurrency = ContractCurrency.NULL;
    private Double sold = 0.0;
    private Boolean swapped = false;
    private Boolean autoSell = false;
    private Double target = 0.0;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
}
