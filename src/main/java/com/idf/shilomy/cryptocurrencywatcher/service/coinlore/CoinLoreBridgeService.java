package com.idf.shilomy.cryptocurrencywatcher.service.coinlore;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.idf.shilomy.cryptocurrencywatcher.dto.CurrencyDto;
import com.idf.shilomy.cryptocurrencywatcher.model.Currency;
import com.idf.shilomy.cryptocurrencywatcher.service.BridgeService;
import com.idf.shilomy.cryptocurrencywatcher.service.currency.CurrencyLoaderService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Сервис связи с API CoinLore
 */
@Component("CoinLoreBridgeService")
@Service
@RequiredArgsConstructor
public class CoinLoreBridgeService implements BridgeService {

    /**
     * Репозиторий валют
     */
    private final CurrencyLoaderService currencyLoaderService;

    private static final String COIN_LORE_API_URL = "https://api.coinlore.net/api/ticker/";
    private static final String ID = "id";
    private static final String COMMA_DELIMITER = ",";

    /**
     * Получение информации о валюте
     * @return список объектов передачи валют
     */
    @Override
    @SneakyThrows
    public List<CurrencyDto> getCoinInfo() {
        //Получение валют из конфигурационного файла
        List<Long> coinIds = currencyLoaderService.getCoinList().stream().map(Currency::getId).toList();

        //Создание пути запроса
        URI api = UriComponentsBuilder
            .fromUriString(COIN_LORE_API_URL)
            .queryParam(ID, coinIds.stream().map(Object::toString).collect(Collectors.joining(COMMA_DELIMITER)))
            .build()
            .toUri();

        //Создание запроса
        HttpRequest request = HttpRequest.newBuilder()
            .uri(api)
            .GET()
            .build();
        HttpClient client = HttpClient.newHttpClient();

        //Отправка запроса и получение ответа
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(response.body(), new TypeReference<>() {});
    }
}