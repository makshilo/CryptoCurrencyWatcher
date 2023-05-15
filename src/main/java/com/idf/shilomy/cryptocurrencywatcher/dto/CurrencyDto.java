package com.idf.shilomy.cryptocurrencywatcher.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Объект передачи валюты
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"name", "nameid", "rank", "percent_change_24h", "percent_change_1h", "percent_change_7d",
    "market_cap_usd", "volume24", "volume24_native", "csupply", "price_btc", "tsupply", "msupply"})
public class CurrencyDto {

    /**
     * Идентификатор
     */
    private Long id;

    /**
     * Символ
     */
    private String symbol;

    /**
     * Цена
     */
    @JsonAlias({"price_usd"})
    private BigDecimal priceUsd;
}
