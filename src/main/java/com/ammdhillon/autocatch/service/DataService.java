package com.ammdhillon.autocatch.service;

import com.ammdhillon.autocatch.model.entity_model.data.Data;
import com.ammdhillon.autocatch.repo.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    @Autowired
    private DataRepository repo;

    public void saveData(Data data) {
        data.setId(getData().getId());
        repo.save(data);
    }

    public Data getData() {
        if (repo.findAll().isEmpty()) {
            return repo.save(new Data());
        } else {
            return repo.findAll().get(0);
        }
    }

    public Boolean isActive() {
        return getData().getIsActive();
    }

    public Boolean isTesting() {
        return getData().getIsTesting();
    }

    public Boolean isSchedulerActive() {
        return getData().getIsSchedulerActive();
    }

    public Double getBuyAmountETH() {
        return getData().getBuyAmountETH();
    }

    public Double getBuyAmountBNB() {
        return getData().getBuyAmountBNB();
    }

    public Double getBuySlippage() {
        return getData().getBuySlippage();
    }

    public Double getSellSlippage() {
        return getData().getSellSlippage();
    }

    public String getAnnouncementUrl() {
        return getData().getAnnouncementUrl();
    }

    public String getMyAddress() {
        return getData().getMyAddress();
    }

    public String getJeremy() {
        return getData().getJeremy();
    }

    public String getMainNodeETH() {
        return getData().getMainNodeETH();
    }

    public String getTestNodeETH() {
        return getData().getTestNodeETH();
    }

    public String getTestNodePOLYGON() {
        return getData().getTestNodePOLYGON();
    }

    public String getMainNodePOLYGON() {
        return getData().getMainNodePOLYGON();
    }

    public Double getSellTarget() {
        return getData().getSellTarget();
    }

    public Boolean getAutoSell() {
        return getData().getAutoSell();
    }

    public Boolean getNotifications() {
        return getData().getNotifications();
    }

//    public Map<Long, Double> getTimePercentage() {
//        return getData().getTimePercentage();
//    }

}
