package com.kumbirai.spring.microservices.currencyconversionservice;

import com.kumbirai.rest.webservices.exception.CustomizedResponseEntityExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients
@Import({CustomizedResponseEntityExceptionHandler.class})
public class CurrencyConversionServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(CurrencyConversionServiceApplication.class, args);
    }
}
