package com.idf.shilomy.cryptocurrencywatcher.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idf.shilomy.cryptocurrencywatcher.dto.CurrencyDto;
import com.idf.shilomy.cryptocurrencywatcher.service.controller.ActualPriceService;
import com.idf.shilomy.cryptocurrencywatcher.service.controller.CurrencyListService;
import com.idf.shilomy.cryptocurrencywatcher.service.controller.SubscriptionService;

import lombok.RequiredArgsConstructor;

/**
 * Контроллер действий с валютами
 */
@RestController
@RequiredArgsConstructor
public class CurrencyController {

    /**
     * Сервис получения списка валют
     */
    private final CurrencyListService currencyListService;

    /**
     * Сервис получения актуальной цены
     */
    private final ActualPriceService actualPriceService;

    /**
     * Сервис подписки на валюту
     */
    private final SubscriptionService subscriptionService;

    /**
     * Метод получения списка валют
     * @return список валют
     */
    @GetMapping("/list")
    public List<CurrencyDto> currencyList() {
        return currencyListService.getCurrencyList();
    }

    /**
     * Метод получения актуальной цены валюты
     * @param symbol символ валюты
     * @return цена
     */
    @GetMapping("/actual")
    public BigDecimal actualPrice(String symbol) {
        return actualPriceService.getActualPrice(symbol);
    }

    /**
     * Метод подписки на валюту
     * @param userName имя пользователя
     * @param symbol символ валюты
     */
    @GetMapping("/notify")
    public void subscribe(String userName, String symbol) {
        subscriptionService.subscribe(userName, symbol);
    }
}
