package com.in28minutes.microservices.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.ExchangeValueRepository;
import com.in28minutes.microservices.model.ExchangeValue;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment enviroment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from,
			@PathVariable String to) {
		
		
		
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		
		exchangeValue.setPort(Integer.parseInt(enviroment.getProperty("local.server.port")));
		
		return exchangeValue;
	}
}
