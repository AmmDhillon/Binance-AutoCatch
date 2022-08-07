package com.ammdhillon.autocatch.model.entity_model.data;

import com.ammdhillon.autocatch.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "data")
public class Data {

    @Id
    @SequenceGenerator(sequenceName = "data_sequence", allocationSize = 1, name = "data_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "data_sequence")
    private Long id;
    private Double buyAmountETH = 0.05;
    private Double buyAmountBNB = 0.05;
    private Double buyAmountMATIC = 0.05;
    private Double buySlippage = 0.015;
    private Double sellSlippage = 0.03;
    private Double sellTarget = 0.5;
    private Boolean isActive = false;
    private Boolean isSchedulerActive = true;
    private Boolean isTesting = true;
    private Boolean autoSell = false;
    private Boolean notifications = false;
    private String myAddress = "";
    private String jeremy = "";
    private String announcementUrl = Constants.ANNOUNCEMENT_URL;
    private String mainNodeETH = Constants.MAIN_NODE_ETH;
    private String testNodeETH = Constants.TEST_NODE_ETH;
    private String mainNodeBSC = Constants.MAIN_NODE_BSC;
    private String testNodeBSC = Constants.TEST_NODE_BSC;
    private String mainNodePOLYGON = Constants.MAIN_NODE_POLYGON;
    private String testNodePOLYGON = Constants.TEST_NODE_POLYGON;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
}
