package com.systems.telegram.bot.southpool.controller.tranportation.profile.response.pages;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageResult {

	@JsonProperty("total_count")
	private Integer totalCount;
	@JsonProperty("pages")
	private List<Page> pages = Collections.emptyList();
	
}