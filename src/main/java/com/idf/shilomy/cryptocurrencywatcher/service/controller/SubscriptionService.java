package com.idf.shilomy.cryptocurrencywatcher.service.controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.idf.shilomy.cryptocurrencywatcher.model.User;
import com.idf.shilomy.cryptocurrencywatcher.repository.CurrencyRepository;

import lombok.RequiredArgsConstructor;

/**
 * Сервис подписки на курс валюты
 */
@Component
@Service
@RequiredArgsConstructor
public class SubscriptionService {

    /**
     * Репозиторий валют
     */
    private final CurrencyRepository currencyRepository;

    /**
     * Метод подписки на курс валюты
     * @param userName имя пользователя
     * @param symbol символ валюты
     */
    public void subscribe(String userName, String symbol) {
        User user = new User();
        user.setName(userName);

        currencyRepository.findAll().stream()
            .filter(currency -> currency.getSymbol().equals(symbol))
            .forEach(currency -> {
                currency.getFollowers().add(user);
                currencyRepository.save(currency);
            });
    }
}
