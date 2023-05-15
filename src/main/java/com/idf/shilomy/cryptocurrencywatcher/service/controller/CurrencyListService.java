package com.idf.shilomy.cryptocurrencywatcher.service.controller;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.idf.shilomy.cryptocurrencywatcher.dto.CurrencyConverter;
import com.idf.shilomy.cryptocurrencywatcher.dto.CurrencyDto;
import com.idf.shilomy.cryptocurrencywatcher.model.Currency;
import com.idf.shilomy.cryptocurrencywatcher.repository.CurrencyRepository;

import lombok.RequiredArgsConstructor;

/**
 * Сервис получения списка валют
 */
@Component
@Service
@RequiredArgsConstructor
public class CurrencyListService {

    /**
     * Репозиторий валют
     */
    private final CurrencyRepository currencyRepository;

    /**
     * Метод получения списка валют
     * @return список валют
     */
    public List<CurrencyDto> getCurrencyList() {
        List<Currency> currencyList = currencyRepository.findAll();
        return currencyList.stream().map(CurrencyConverter::convertToDto).toList();
    }
}
