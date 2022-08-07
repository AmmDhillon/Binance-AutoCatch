package com.ammdhillon.autocatch.service;

import com.ammdhillon.autocatch.model.entity_model.swap_transaction.SwapTransaction;
import com.ammdhillon.autocatch.model.enums.Status;
import com.ammdhillon.autocatch.repo.SwapTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwapTransactionService {

    @Autowired
    private SwapTransactionRepository repo;

    public void saveTransaction(SwapTransaction transaction) {
        repo.save(transaction);
    }

    public List<SwapTransaction> getPending() {
        return repo.findByStatus(Status.PENDING, Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<SwapTransaction> getAll() {
        return repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
