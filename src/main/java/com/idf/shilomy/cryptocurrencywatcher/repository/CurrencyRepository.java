package com.idf.shilomy.cryptocurrencywatcher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idf.shilomy.cryptocurrencywatcher.model.Currency;

/**
 * Репозиторий валюты
 */
@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}
