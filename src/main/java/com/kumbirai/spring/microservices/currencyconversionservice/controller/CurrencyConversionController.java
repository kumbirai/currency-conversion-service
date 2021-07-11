/*
 Copyright (c) 2021

 All rights reserved.
 */
package com.kumbirai.spring.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.kumbirai.spring.microservices.currencyconversionservice.bean.CurrencyConversion;
import com.kumbirai.spring.microservices.currencyconversionservice.proxy.CurrencyExchangeProxy;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> CurrencyConversionController<br>
 * <br>
 *
 * @author Kumbirai 'Coach' Mundangepfupfu<br>
 * @date 30 May 2021<br>
 * @version 1.0<br>
 *
 * <b>Revision:</b>
 * <br>
 *					
 */
public class CurrencyConversionController
{
	private static final Logger LOGGER = LogManager.getLogger(CurrencyConversionController.class.getName());

	@Autowired
	private CurrencyExchangeProxy proxy;

	/**
	 * Constructor:
	 */
	public CurrencyConversionController()
	{
		super();
		LOGGER.trace("CurrencyConversionController.CONSTRUCTOR");
	}

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity)
	{
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				CurrencyConversion.class, uriVariables);

		var currencyConversion = responseEntity.getBody();

		Objects.requireNonNull(currencyConversion, "Currency Conversion is null");

		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment() + ": " + "rest template");
	}

	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity)
	{
		var currencyConversion = proxy.retrieveExchangeValue(from, to);

		return new CurrencyConversion(currencyConversion.getId(), from, to, quantity, currencyConversion.getConversionMultiple(),
				quantity.multiply(currencyConversion.getConversionMultiple()), currencyConversion.getEnvironment() + ": " + "feign");
	}
}
