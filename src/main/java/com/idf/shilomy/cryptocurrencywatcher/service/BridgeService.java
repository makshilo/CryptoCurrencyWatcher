package com.idf.shilomy.cryptocurrencywatcher.service;

import java.util.List;

import com.idf.shilomy.cryptocurrencywatcher.dto.CurrencyDto;

/**
 * Интерфейс сервиса получения информации о валюте
 */
public interface BridgeService {

    /**
     * Метод получения информации о валюте
     * @return список объектов передачи валют
     */
    List<CurrencyDto> getCoinInfo();
}
