package com.systems.telegram.bot.southpool.controller.tranportation.profile.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultPage {

	@JsonProperty("short_name")
	private String shortName;
	@JsonProperty("author_name")
	private String authorName;
	@JsonProperty("author_url")
	private String authorUrl;
	@JsonProperty("access_token")
	private String accessToken;
	@JsonProperty("auth_url")
	private String authUrl;
}