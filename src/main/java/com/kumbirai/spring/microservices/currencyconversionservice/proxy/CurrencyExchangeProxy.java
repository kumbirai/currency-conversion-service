/*
 Copyright (c) 2021

 All rights reserved.
 */
package com.kumbirai.spring.microservices.currencyconversionservice.proxy;

import com.kumbirai.spring.microservices.currencyconversionservice.bean.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> CurrencyExchangeProxy<br>
 * <br>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 * <br>
 */
// @FeignClient(name="currency-exchange", url="localhost:8000")
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy
{
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
