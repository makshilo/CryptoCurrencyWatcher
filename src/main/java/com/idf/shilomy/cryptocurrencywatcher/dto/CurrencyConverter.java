package com.idf.shilomy.cryptocurrencywatcher.dto;

import com.idf.shilomy.cryptocurrencywatcher.model.Currency;

/**
 * Конвертер между моделью и объектом передачи валюты
 */
public class CurrencyConverter {

    /**
     * Метод конвертирования в модель
     * @param currencyDto объект передачи
     * @return модель
     */
    public static Currency convertToModel(CurrencyDto currencyDto) {
        Currency currency = new Currency();
        currency.setId(currencyDto.getId());
        currency.setSymbol(currencyDto.getSymbol());
        currency.setPrice(currencyDto.getPriceUsd());

        return currency;
    }

    /**
     * Метод конвертирования в объект передачи
     * @param currency модель
     * @return объект передачи
     */
    public static CurrencyDto convertToDto(Currency currency) {
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setId(currency.getId());
        currencyDto.setSymbol(currency.getSymbol());
        currencyDto.setPriceUsd(currency.getPrice());

        return currencyDto;
    }
}
