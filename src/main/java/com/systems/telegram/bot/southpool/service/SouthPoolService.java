package com.systems.telegram.bot.southpool.service;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.systems.telegram.bot.southpool.controller.comment.response.Comment;
import com.systems.telegram.bot.southpool.controller.tranportation.profile.response.ProfilePage;
import com.systems.telegram.bot.southpool.controller.tranportation.profile.response.pages.Pages;
import com.systems.telegram.bot.southpool.controller.tranportation.profile.response.updateprofile.UpdateProfileResponse;
import com.systems.telegram.bot.southpool.entities.Member;
import com.systems.telegram.bot.southpool.net.rest.RESTHttpClient;
import com.systems.telegram.bot.southpool.props.SouthPoolSettings;

@Service
public class SouthPoolService {
	
	private static final Log log = LogFactory.getLog(SouthPoolService.class);

	private RESTHttpClient restHttpClient;
	
	public SouthPoolService(RESTHttpClient restHttpClient) {
		super();
		this.restHttpClient = restHttpClient;
	}
	
	public Comment sendMessageComment(String text, long userChatId, SouthPoolSettings southPoolSettings) {
		String url = "https://api.comments.bot/createPost?";
		Comment comment = null;
		log.info(url);
		try {			
			MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
			param.add("api_key", southPoolSettings.getCommentBotToken());
			param.add("owner_id", String.valueOf(userChatId));
			param.add("type", "text");
			param.add("parse_mode", "HTML");
			param.add("text", text.replace("&", "and"));
			param.add("disable_web_page_preview", "true");
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(param, null);
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request));
			comment = restHttpClient.getDefaultRestTemplate().exchange(url, HttpMethod.POST, request, Comment.class).getBody();
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(comment));
		} catch (RestClientException e) {
			log.error("error "+e);
		} catch (JsonProcessingException e) {
			log.error("error "+e);
		}
		return comment;
	}
	
	public void sendMessage(String text, String groupChatId, SouthPoolSettings southPoolSettings) throws UnsupportedEncodingException {
		String url = MessageFormat.format(southPoolSettings.getTelegramApiUrl(), southPoolSettings.getGroupchatSenderBotToken());
		String result = null;
		try {
			MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
			param.add("chat_id", String.valueOf(groupChatId));
			param.add("parse_mode", "HTML");
			param.add("text", text.replace("&", "and"));
			param.add("disable_web_page_preview", "true");
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(param, null);
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request));
			result = restHttpClient.getDefaultRestTemplate().exchange(url, HttpMethod.POST, request, String.class).getBody();
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result));
		} catch (RestClientException e) {
			log.error(e);
		} catch (JsonProcessingException e) {
			log.error(e);
		}
		log.info(result);
	}
	
	@Async
	public void sendMessageToAdmin(String text, String groupChatId, SouthPoolSettings southPoolSettings) throws UnsupportedEncodingException {
		String url = MessageFormat.format(southPoolSettings.getTelegramApiUrl(), southPoolSettings.getGroupchatAdminsConcernSernderBot());
		String result = null;
		try {
			MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
			param.add("chat_id", String.valueOf(groupChatId));
			param.add("parse_mode", "HTML");
			param.add("text", text.replace("&", "and"));
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(param, null);
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request));
			result = restHttpClient.getDefaultRestTemplate().exchange(url, HttpMethod.POST, request, String.class).getBody();
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result));
		} catch (RestClientException e) {
			log.error(e);
		} catch (JsonProcessingException e) {
			log.error(e);
		}
		log.info(result);
	}
	
	@Async
	public void sendMessageToFollowers(String text, long chatId, SouthPoolSettings southPoolSettings) throws UnsupportedEncodingException {
		String url = MessageFormat.format(southPoolSettings.getTelegramApiUrl(), "bot"+southPoolSettings.getTelegramBotToken());
		String result = null;
		try {
			MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
			param.add("chat_id", String.valueOf(chatId));
			param.add("parse_mode", "HTML");
			param.add("text", text.replace("&", "and"));
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(param, null);
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request));
			result = restHttpClient.getDefaultRestTemplate().exchange(url, HttpMethod.POST, request, String.class).getBody();
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(result));
		} catch (RestClientException e) {
			log.error(e);
		} catch (JsonProcessingException e) {
			log.error(e);
		}
		log.info(result);
	}
	
	
	public ProfilePage sendMessageCreatePage(Member member) {
		String url = MessageFormat.format("https://api.telegra.ph/createAccount?short_name={0}&author_name={1}", member.getUsername(), !"".equals(member.getName()) ? member.getName() : member.getUsername());
		
		ProfilePage profilePage = null;
		log.info(url);
		try {
			String sendRequest = UriComponentsBuilder.fromUriString(url).build().toUriString();
			log.info(sendRequest);
			profilePage = restHttpClient.getDefaultRestTemplate().exchange(sendRequest, HttpMethod.GET, null, ProfilePage.class).getBody();
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(profilePage));
		} catch (RestClientException e) {
			log.error("error "+e);
		} catch (JsonProcessingException e) {
			log.error("error "+e);
		}
		return profilePage;
	}
	
	public ProfilePage sendMessageRevokeAccessToken(Member member) {
		String url = MessageFormat.format("https://api.telegra.ph/revokeAccessToken?access_token={0}", member.getPageAccessToken());
		ProfilePage profilePage = null;
		log.info(url);
		try {
			String sendRequest = UriComponentsBuilder.fromUriString(url).build().toUriString();
			log.info(sendRequest);
			profilePage = restHttpClient.getDefaultRestTemplate().exchange(sendRequest, HttpMethod.GET, null, ProfilePage.class).getBody();
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(profilePage));
		} catch (RestClientException e) {
			log.error("error "+e);
		} catch (JsonProcessingException e) {
			log.error("error "+e);
		}
		return profilePage;
	}
	
	
	public Pages sendMessagePage(Member member) {
		String url = MessageFormat.format("https://api.telegra.ph/getPageList?access_token={0}&limit=10", member.getPageAccessToken());
		Pages pages = null;
		log.info(url);
		try {
			String sendRequest = UriComponentsBuilder.fromUriString(url).build().toUriString();
			log.info(sendRequest);
			pages = restHttpClient.getDefaultRestTemplate().exchange(sendRequest, HttpMethod.GET, null, Pages.class).getBody();
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(pages));
		} catch (RestClientException e) {
			log.error("error "+e);
		} catch (JsonProcessingException e) {
			log.error("error "+e);
		}
		return pages;
	}
	
	public Comment sendMessageProfileEnableComment(String text, String photoURL, long userChatId, SouthPoolSettings southPoolSettings) {
		String url = "https://api.comments.bot/createPost?";
		Comment comment = null;
		log.info(url);
		try {
			MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
			param.add("api_key", southPoolSettings.getCommentBotToken());
			param.add("owner_id", String.valueOf(userChatId));
			param.add("type", "photo");
			param.add("photo_url", photoURL);
			param.add("parse_mode", "HTML");
			param.add("caption", text.replace("&", "and"));
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(param, null);
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request));
			comment = restHttpClient.getDefaultRestTemplate().exchange(url, HttpMethod.POST, request, Comment.class).getBody();
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(comment));
		} catch (RestClientException e) {
			log.error("error "+e);
		} catch (JsonProcessingException e) {
			log.error("error "+e);
		}
		return comment;
	}
	
	public UpdateProfileResponse updateMessageProfileEnableComment(String text, String photoURL, String postId, SouthPoolSettings southPoolSettings) {
		String url = "https://api.comments.bot/editPost?";
		UpdateProfileResponse comment = null;
		log.info(url);
		try {
			MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
			param.add("api_key", southPoolSettings.getCommentBotToken());
			param.add("post_id", postId);
			param.add("type", "photo");
			param.add("photo_url", photoURL);
			param.add("parse_mode", "HTML");
			param.add("caption", text.replace("&", "and"));
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(param, null);
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(request));
			comment = restHttpClient.getDefaultRestTemplate().exchange(url, HttpMethod.POST, request, UpdateProfileResponse.class).getBody();
			log.info(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(comment));
		} catch (RestClientException e) {
			log.error("error "+e);
		} catch (JsonProcessingException e) {
			log.error("error "+e);
		}
		return comment;
	}
}
