package com.idf.shilomy.cryptocurrencywatcher.service.controller;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.idf.shilomy.cryptocurrencywatcher.dto.CurrencyConverter;
import com.idf.shilomy.cryptocurrencywatcher.repository.CurrencyRepository;

import lombok.RequiredArgsConstructor;

/**
 * Сервис получения актуальной цены
 */
@Component
@Service
@RequiredArgsConstructor
public class ActualPriceService {

    /**
     * Репозиторий валют
     */
    private final CurrencyRepository currencyRepository;

    /**
     * Метод получения актуальной цены
     * @param symbol символ валюты
     * @return цена
     */
    public BigDecimal getActualPrice(String symbol) {
        return currencyRepository.findAll().stream()
            .map(CurrencyConverter::convertToDto)
            .filter(currency -> currency.getSymbol().equals(symbol))
            .findFirst()
            .orElseThrow()
            .getPriceUsd();
    }
}
