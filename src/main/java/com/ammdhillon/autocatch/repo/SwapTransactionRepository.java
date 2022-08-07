package com.ammdhillon.autocatch.repo;

import com.ammdhillon.autocatch.model.entity_model.swap_transaction.SwapTransaction;
import com.ammdhillon.autocatch.model.enums.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface SwapTransactionRepository extends JpaRepository<SwapTransaction, Long> {

    List<SwapTransaction> findByStatus(Status status, Sort sort);
}