package com.idf.shilomy.cryptocurrencywatcher.service.currency;

import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idf.shilomy.cryptocurrencywatcher.model.Currency;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Сервис получения валют из конфигурации сервера
 */
@Component
@Service
@RequiredArgsConstructor
public class CurrencyLoaderService {

    /**
     * Загрузчик ресурсов
     */
    private final ResourceLoader resourceLoader;

    /**
     * Преобразователь в объекты
     */
    private final ObjectMapper objectMapper;

    private static final String CONFIG_PATH = "classpath:coins.json";

    /**
     * Метод получения списка валют из конфигурации сервера
     * @return список доступных валют
     */
    @SneakyThrows
    public List<Currency> getCoinList() {
        Resource resource = resourceLoader.getResource(CONFIG_PATH);
        InputStream inputStream = resource.getInputStream();
        return objectMapper.readValue(inputStream, new TypeReference<>() {});
    }
}
