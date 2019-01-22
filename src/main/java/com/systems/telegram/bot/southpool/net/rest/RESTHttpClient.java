package com.systems.telegram.bot.southpool.net.rest;

import org.springframework.web.client.RestTemplate;

public interface RESTHttpClient {

	public RestTemplate getDefaultRestTemplate();
}
