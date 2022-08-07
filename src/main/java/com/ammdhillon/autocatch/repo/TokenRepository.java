package com.ammdhillon.autocatch.repo;

import com.ammdhillon.autocatch.model.entity_model.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByNameAndSymbol(String name, String symbol);

    Optional<Token> findByBinanceId(Long binanceId);

    List<Token> findBySwappedTrue();

    List<Token> findByAutoSellTrue();
}