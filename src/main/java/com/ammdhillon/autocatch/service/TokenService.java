package com.ammdhillon.autocatch.service;

import com.ammdhillon.autocatch.model.entity_model.token.Token;
import com.ammdhillon.autocatch.repo.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenService {

    @Autowired
    private TokenRepository repo;

    public Token saveToken(Token token) {
        return repo.save(token);
    }

    public Token saveToken(Long id, Boolean autoSell) {
        Token token = getToken(id);

        if (token == null) return null;

        token.setAutoSell(autoSell);

        return saveToken(token);
    }

    public void removeToken(Token token) {
        repo.findByNameAndSymbol(token.getName(), token.getSymbol()).ifPresent(repo::delete);
    }

    public List<Token> getSwapped() {
        return repo.findBySwappedTrue();
    }

    public List<Token> getAutoSell() {
        return repo.findByAutoSellTrue();
    }

    public List<Token> getAll() {
        return repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Boolean isTokenExists(Token token) {
        return repo.findByBinanceId(token.getBinanceId()).isPresent();
    }

    public Token getToken(Long id) {
        return repo.findById(id).orElse(null);
    }
}