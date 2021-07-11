/*
 Copyright (c) 2021

 All rights reserved.
 */
package com.kumbirai.spring.microservices.currencyconversionservice.bean;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p><b>Purpose:</b><br>
 * <br>
 *
 * <p><b>Title:</b> CurrencyConversion<br>
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
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversion
{
	private Long id;
	private String from;
	private String to;
	private BigDecimal quantity;
	private BigDecimal conversionMultiple;
	private BigDecimal totalCalculatedAmount;
	private String environment;
}
