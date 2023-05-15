package com.idf.shilomy.cryptocurrencywatcher.service.notification;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.idf.shilomy.cryptocurrencywatcher.model.Currency;
import com.idf.shilomy.cryptocurrencywatcher.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

/**
 * Сеовис оповещения подпищиков
 */
@Component("LoggerNotificationService")
@Service
@Slf4j
public class LoggerNotificationService implements NotificationService {

    private static final String SPACE = " ";

    /**
     * Метод оповещения подпищиков валюты
     * @param currency валюта
     * @param difference разница в процентах
     */
    @Override
    public void notifyUsers(Currency currency, Double difference) {
        currency.getFollowers().forEach(user ->
            log.warn(String.join(SPACE, currency.getSymbol(), user.getName(), difference.toString())));
    }
}
