package com.idf.shilomy.cryptocurrencywatcher.service.currency;

import java.util.AbstractMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.idf.shilomy.cryptocurrencywatcher.dto.CurrencyConverter;
import com.idf.shilomy.cryptocurrencywatcher.model.Currency;
import com.idf.shilomy.cryptocurrencywatcher.repository.CurrencyRepository;
import com.idf.shilomy.cryptocurrencywatcher.service.BridgeService;
import com.idf.shilomy.cryptocurrencywatcher.service.NotificationService;

import lombok.RequiredArgsConstructor;

/**
 * Сервис обработки валют
 */
@Component
@Service
@RequiredArgsConstructor
public class CurrencyFetcherService {

    /**
     * Сервис связи с API CoinLore
     */
    @Qualifier("CoinLoreBridgeService")
    private final BridgeService coinLoreBridgeService;

    /**
     * Сервис оповещений
     */
    @Qualifier("LoggerNotificationService")
    private final NotificationService loggerNotificationService;

    /**
     * Репозиторий валют
     */
    private final CurrencyRepository currencyRepository;

    /**
     * Метод получения и обработки данных
     */
    @Scheduled(fixedRate = 60000)
    public void fetchData() {
        List<Currency> savedCurrencies = currencyRepository.findAll();
        List<Currency> actualCurrencies = coinLoreBridgeService.getCoinInfo().stream().map(CurrencyConverter::convertToModel).toList();

        if (!savedCurrencies.isEmpty()) {
            savedCurrencies.stream()
                .flatMap(saved -> actualCurrencies.stream()
                    .filter(actual -> saved.getSymbol().equals(actual.getSymbol()))
                    .map(actual -> new AbstractMap.SimpleEntry<>(actual, calculatePercentageDifference(saved, actual))))
                .filter(entry -> Math.abs(entry.getValue()) > 1)
                .forEach(entry -> loggerNotificationService.notifyUsers(entry.getKey(), entry.getValue()));
        }

        currencyRepository.saveAll(actualCurrencies);
    }

    /**
     * Подсчёт процента изменения курса валюты
     * @param savedCurrency сохранённая валюта
     * @param actualCurrency актуальная валюта
     * @return процент изменения
     */
    private double calculatePercentageDifference(Currency savedCurrency, Currency actualCurrency) {
        double oldValue = savedCurrency.getPrice().doubleValue();
        double newValue = actualCurrency.getPrice().doubleValue();

        double difference = newValue - oldValue;
        return (difference / oldValue) * 100;
    }
}
