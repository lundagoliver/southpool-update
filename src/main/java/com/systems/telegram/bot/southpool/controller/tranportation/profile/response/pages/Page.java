package com.systems.telegram.bot.southpool.controller.tranportation.profile.response.pages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Page {

	@JsonProperty("path")
	private String path;
	@JsonProperty("url")
	private String url;
	@JsonProperty("title")
	private String title;
	@JsonProperty("description")
	private String description;
	@JsonProperty("author_name")
	private String authorName;
	@JsonProperty("image_url")
	private String imageUrl="";
	@JsonProperty("views")
	private Integer views;
	@JsonProperty("can_edit")
	private Boolean canEdit;
}