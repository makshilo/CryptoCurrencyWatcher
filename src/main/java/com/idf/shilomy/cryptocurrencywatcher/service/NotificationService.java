package com.idf.shilomy.cryptocurrencywatcher.service;

import com.idf.shilomy.cryptocurrencywatcher.model.Currency;

/**
 * Интерфейс сервиса уведомлений
 */
public interface NotificationService {

    /**
     * Метод уведомления
     * @param currency валюта
     * @param difference разница
     */
    void notifyUsers(Currency currency, Double difference);
}
