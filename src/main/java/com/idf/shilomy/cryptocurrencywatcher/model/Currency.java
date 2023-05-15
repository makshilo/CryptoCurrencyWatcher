package com.idf.shilomy.cryptocurrencywatcher.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

/**
 * Модель валюты
 */
@Entity
@Getter
@Setter
public class Currency {

    /**
     * Идентификатор
     */
    @Id
    private Long id;

    /**
     * Символ
     */
    @Column(name = "symbol")
    private String symbol;

    /**
     * Цена
     */
    private BigDecimal price;

    /**
     * Подписчики
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        joinColumns = {@JoinColumn(name = "currency_id")},
        inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> followers;
}
