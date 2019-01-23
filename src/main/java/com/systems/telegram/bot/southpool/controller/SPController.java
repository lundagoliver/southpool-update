package com.systems.telegram.bot.southpool.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.itextpdf.text.DocumentException;
import com.systems.telegram.bot.southpool.controller.postrequest.Post;
import com.systems.telegram.bot.southpool.controller.questions.QuestionAndAnswer;
import com.systems.telegram.bot.southpool.controller.register.RegisterMember;
import com.systems.telegram.bot.southpool.controller.search.Search;
import com.systems.telegram.bot.southpool.controller.setusername.SetTelegramUserName;
import com.systems.telegram.bot.southpool.controller.tranportation.Home2Work;
import com.systems.telegram.bot.southpool.controller.tranportation.Work2Home;
import com.systems.telegram.bot.southpool.controller.update.UpdateMember;
import com.systems.telegram.bot.southpool.entities.Followers;
import com.systems.telegram.bot.southpool.entities.Member;
import com.systems.telegram.bot.southpool.entities.PreviousMessage;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberHomeToWork;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberWorkToHome;
import com.systems.telegram.bot.southpool.persistence.service.PersistenceService;
import com.systems.telegram.bot.southpool.props.SouthPoolSettings;
import com.systems.telegram.bot.southpool.service.SouthPoolService;
import com.systems.telegram.bot.southpool.service.SouthpoolSearchService;
import com.systems.telegram.bot.southpool.utility.MemberValidation;
import com.systems.telegram.bot.southpool.utility.callback.CallbackCommands;
import com.systems.telegram.bot.southpool.utility.date.DateUtility;
import com.systems.telegram.bot.southpool.utility.menu.InlineKeyboardBuilder;
import com.systems.telegram.bot.southpool.utility.message.ConstantMessage;
import com.systems.telegram.bot.southpool.utility.time.TimeUtility;
import com.vdurmont.emoji.EmojiParser;

@Component
public class SPController extends TelegramLongPollingBot {

	private static final Log log = LogFactory.getLog(TelegramLongPollingBot.class);

	private SouthPoolSettings southPoolSettings;
	private PersistenceService persistenceService;	
	private SouthPoolService southPoolService;
	private SouthpoolSearchService southpoolSearchService;

	private List<KeyboardRow> seatKeyBoard = new ArrayList<>();
	private List<KeyboardRow> timeKeyBoard = new ArrayList<>();
	private List<KeyboardRow> waitingTimeKeyBoard = new ArrayList<>();
	private List<String> waitingTime = new ArrayList<>();

	public SPController(SouthPoolSettings southPoolSettings, PersistenceService persistenceService,
			SouthPoolService southPoolService, SouthpoolSearchService southpoolSearchService) {
		super();
		this.southPoolSettings = southPoolSettings;
		this.persistenceService = persistenceService;
		this.southPoolService = southPoolService;
		this.southpoolSearchService = southpoolSearchService;
	}

	@PostConstruct
	private void init() {

		for (int i = 1; i<=20; i++) {
			KeyboardRow key = new KeyboardRow();
			key.add(Integer.toString(i));
			seatKeyBoard.add(key);
		}

		for (Integer i = 0; i<24; i++) {
			KeyboardRow key = new KeyboardRow();
			key.add(i < 12 ? i == 0 ? "12" + ":00 AM" : i + ":00 AM" : TimeUtility.convertMilitaryToStandardTime(String.valueOf(i))+":00 PM");
			key.add(i < 12 ? i == 0 ? "12" + ":15 AM" : i + ":15 AM" : TimeUtility.convertMilitaryToStandardTime(String.valueOf(i))+":15 PM");
			key.add(i < 12 ? i == 0 ? "12" + ":30 AM" : i + ":30 AM" : TimeUtility.convertMilitaryToStandardTime(String.valueOf(i))+":30 PM");
			key.add(i < 12 ? i == 0 ? "12" + ":45 AM" : i + ":45 AM" : TimeUtility.convertMilitaryToStandardTime(String.valueOf(i))+":45 PM");
			timeKeyBoard.add(key);
		}

		waitingTime = new ArrayList<>();
		waitingTime.add("05-minutes");
		waitingTime.add("10-minutes");
		waitingTime.add("15-minutes");
		waitingTime.add("20-minutes");
		waitingTime.add("25-minutes");
		waitingTime.add("30-minutes");
		waitingTime.add("35-minutes");
		waitingTime.add("40-minutes");
		waitingTime.add("45-minutes");
		waitingTime.add("50-minutes");
		waitingTime.add("55-minutes");
		waitingTime.add("60-minutes");
		for (String wait : waitingTime) {
			KeyboardRow key = new KeyboardRow();
			key.add(wait);
			waitingTimeKeyBoard.add(key);
		}
	}

	@Override
	public void onUpdateReceived(Update update) {

		if (update.hasMessage() && update.getMessage().hasText()) {

			String username = update.getMessage().getChat().getUserName();
			String messageText = update.getMessage().getText();
			long chatId = update.getMessage().getChatId();

			//Initialize SendMessage
			SendMessage message = new SendMessage();
			message.setChatId(chatId).setParseMode("HTML");

			//Check if username is set already. If username is not yet set, show some information how to do it.
			if (username == null) {
				message.setText(ConstantMessage.SET_USERNAME_MESSAGE);
				message.setReplyMarkup(ConstantMessage.shownOptionsForSettingUserName());
				sendMessage(message);
				return;
			}

			SouthPoolMemberHomeToWork southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			SouthPoolMemberWorkToHome southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

			ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
			message.setReplyMarkup(replyKeyboardMarkup);
			replyKeyboardMarkup.setSelective(true);
			replyKeyboardMarkup.setResizeKeyboard(true);
			replyKeyboardMarkup.setOneTimeKeyboard(true);

			if (southPoolMemberHomeToWork == null || southPoolMemberWorkToHome == null) {
				message.setText(ConstantMessage.notRegisteredMemberMessage());
				message.setReplyMarkup(ConstantMessage.shownOptions());
				sendMessage(message);
			}
			else if ("N".equalsIgnoreCase(southPoolMemberHomeToWork.getAllowed()) || "N".equalsIgnoreCase(southPoolMemberWorkToHome.getAllowed())) {
				String x = EmojiParser.parseToUnicode(":x:");
				message.setText(x+ConstantMessage.BANNED_USER);
				sendMessage(message);
				return;
			}
			else if (messageText.startsWith("/unfollow__")) {
				String[] command = messageText.split("__");
				String userNameOfTheMemberYouFollowed = command[1];

				Map<String,String> uniqueConstraintNameValueMap = new HashMap<>();
				uniqueConstraintNameValueMap.put("username", userNameOfTheMemberYouFollowed);
				uniqueConstraintNameValueMap.put("follower", username);
				Followers followers = persistenceService.getFolowerBy(uniqueConstraintNameValueMap, Followers.class).get(0);
				if (followers == null) {
					String x = EmojiParser.parseToUnicode(":x:");
					message.setText(x+"Unknown username! @"+userNameOfTheMemberYouFollowed);
					sendMessage(message);
					return;
				}
				followers.setActive("N");
				persistenceService.merge(followers);
				message.setText("You have successfully unfollowed @"+userNameOfTheMemberYouFollowed + "!");
				message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
				sendMessage(message);
			}
			else {

				switch  (messageText) {
				case ConstantMessage.START:
					message.setText(ConstantMessage.ACCOUNT_MESSAGE);
					message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
					sendMessage(message);
					break;

				default :

					QuestionAndAnswer.method(persistenceService, username, messageText);

					PreviousMessage previousUserMessage = persistenceService.getMember(username, PreviousMessage.class);
					//Check if user is a registered member. If not, ask the user to register or exit the application.
					southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
					southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

					if (ConstantMessage.VERIFY_MEMBER.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag())) {
						String user = messageText.contains("@") ? messageText.replaceAll("@", "") : messageText; 
						southPoolMemberHomeToWork = persistenceService.getMember(user, SouthPoolMemberHomeToWork.class);
						if (southPoolMemberHomeToWork != null) {
							message.setText(ConstantMessage.verifyMember(southPoolMemberHomeToWork));
							message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
							sendMessage(message);	
						}else{
							message.setText("@"+user + " is not a SOUTHPOOL member!");
							message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
							sendMessage(message);
						}
					}
					else if (ConstantMessage.VERIFY_MEMBER.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
						String user = messageText.contains("@") ? messageText.replaceAll("@", "") : messageText;
						southPoolMemberWorkToHome = persistenceService.getMember(user, SouthPoolMemberWorkToHome.class);
						if (southPoolMemberWorkToHome != null) {
							message.setText(ConstantMessage.verifyMember(southPoolMemberWorkToHome));
							message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
							sendMessage(message);	
						}else{
							message.setText("@"+user + " is not a SOUTHPOOL member!");
							message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
							sendMessage(message);
						}

					}

					else if (ConstantMessage.REPORT_TRAFFIC_STATUS.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag()) || 
							ConstantMessage.REPORT_TRAFFIC_STATUS.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
						try {
							StringBuilder report = new StringBuilder();
							report.append("<b>SOUTHPOOL FLASH REPORT</>").append("\n\n");
							report.append("Details : ").append("\n\n");
							report.append("<i>").append(messageText).append("</i>\n\n");
							report.append("Reported by: @"+username).append("\n");
							southPoolService.sendMessage(report.toString(),southPoolSettings.getGroupChatId(), southPoolSettings);
							String ok = EmojiParser.parseToUnicode(":white_check_mark:");
							message.setText(ok+ConstantMessage.REPORT_POSTED);
							sendMessage(message);
							message.setText(ConstantMessage.ACCOUNT_MESSAGE);
							message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
							sendMessage(message);
						} catch (UnsupportedEncodingException e) {
							log.error("",e);
						}
					}

					else if (ConstantMessage.COMPLAIN_MEMBER_PASSENGER_OR_DRIVER.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag()) || 
							ConstantMessage.COMPLAIN_MEMBER_PASSENGER_OR_DRIVER.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
						StringBuilder report = new StringBuilder();
						report.append("<b>MEMBER CONCERN</>").append("\n\n");
						report.append("Details : ").append("\n\n");
						report.append("<i>").append(messageText).append("</i>\n\n");
						report.append("Complainant: @"+username).append("\n");
						try {
							southPoolService.sendMessageToAdmin(report.toString(),southPoolSettings.getGroupChatIdAdmins(), southPoolSettings);
						} catch (UnsupportedEncodingException e) {
							log.error("{}", e);
						}
						String ok = EmojiParser.parseToUnicode(":white_check_mark:");
						message.setText(ok+ConstantMessage.POSTED_COMPLAIN_MEMBER_PASSENGER_OR_DRIVER);
						sendMessage(message);
						message.setText(ConstantMessage.ACCOUNT_MESSAGE);
						message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
						sendMessage(message);
					}

					else if (ConstantMessage.BAN_MEMBER_TO_USE_THE_BOT.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag()) || 
							ConstantMessage.BAN_MEMBER_TO_USE_THE_BOT.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {

						String user = messageText.contains("@") ? messageText.replaceAll("@", "") : messageText;
						southPoolMemberHomeToWork = persistenceService.getMember(user, SouthPoolMemberHomeToWork.class);
						southPoolMemberWorkToHome = persistenceService.getMember(user, SouthPoolMemberWorkToHome.class);
						if (southPoolMemberHomeToWork == null || southPoolMemberWorkToHome == null) {
							message.setText("User with username " + user + " does not exist!");
							sendMessage(message);	
						}
						else {
							southPoolMemberHomeToWork.setAllowed("N");
							persistenceService.merge(southPoolMemberHomeToWork);
							southPoolMemberWorkToHome.setAllowed("N");
							persistenceService.merge(southPoolMemberWorkToHome);

							StringBuilder report = new StringBuilder();
							report.append("<b>BANNED MEMBER</>").append("\n\n");
							report.append("Details : ").append("\n\n");
							report.append("<i>").append(user + " is banned from SOUTHPOOL community!").append("</i>\n\n");
							report.append("Banned by: @"+username).append("\n");
							try {
								southPoolService.sendMessageToAdmin(report.toString(),southPoolSettings.getGroupChatIdAdmins(), southPoolSettings);
							} catch (UnsupportedEncodingException e) {
								log.error("{}", e);
							}

							String ok = EmojiParser.parseToUnicode(":white_check_mark:");
							message.setText(ok+ConstantMessage.BANNED);
							message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
							sendMessage(message);
						}
						message.setText(ConstantMessage.ACCOUNT_MESSAGE);
						message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
						sendMessage(message);
					}

					else if (ConstantMessage.FOLLOW_A_MEMBER.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag()) || 
							ConstantMessage.FOLLOW_A_MEMBER.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {

						String user = messageText.contains("@") ? messageText.replaceAll("@", "") : messageText;
						southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
						southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

						String chatIdOfFollower = southPoolMemberHomeToWork.getUsername() != null ? southPoolMemberHomeToWork.getChatId() : southPoolMemberWorkToHome.getChatId() != null ? southPoolMemberWorkToHome.getChatId() : "";

						Map<String,String> uniqueConstraintNameValueMap = new HashMap<>();
						uniqueConstraintNameValueMap.put("username", user);
						uniqueConstraintNameValueMap.put("follower", username);
						Followers follower;
						if (persistenceService.findByUniqueConstraint(uniqueConstraintNameValueMap, Followers.class)) {
							follower = (Followers) persistenceService.getFolowerBy(uniqueConstraintNameValueMap, Followers.class).get(0);
							follower.setActive("Y");
							persistenceService.merge(follower);
							String ok = EmojiParser.parseToUnicode(":white_check_mark:");
							message.setText(ok+ConstantMessage.ALREADY_FOLLOWED);
							message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
							sendMessage(message);
						}
						else {
							follower = new Followers();
							follower.setUsername(user);
							follower.setFollower(username);
							follower.setChatId(chatIdOfFollower);
							follower.setActive("Y");
							persistenceService.persist(follower);

							String ok = EmojiParser.parseToUnicode(":white_check_mark:");
							message.setText(ok+ConstantMessage.FOLLOWED);
							message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
							sendMessage(message);
						}
					}

					if ( !ConstantMessage.FOLLOW_A_MEMBER.equals(previousUserMessage.getPrevMessage()) 
							&& !ConstantMessage.COMPLAIN_MEMBER_PASSENGER_OR_DRIVER.equals(previousUserMessage.getPrevMessage()) 
							&& !ConstantMessage.BAN_MEMBER_TO_USE_THE_BOT.equals(previousUserMessage.getPrevMessage()) 
							&& !ConstantMessage.REPORT_TRAFFIC_STATUS.equals(previousUserMessage.getPrevMessage()) 
							&& !ConstantMessage.VERIFY_MEMBER.equals(previousUserMessage.getPrevMessage()) 
							&& CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag())) {

						sendMessage(continuousSaveAndSendMessage(persistenceService, message, replyKeyboardMarkup, southPoolMemberHomeToWork, previousUserMessage, username, PreviousMessage.class));

						if (!MemberValidation.isInfoNotComplete(southPoolMemberHomeToWork)) {
							Home2Work home2Work = new Home2Work(ConstantMessage.HOME2WORK);
							sendMessage(home2Work.proccess(chatId, southPoolMemberHomeToWork));

						}	
					}
					else if (!ConstantMessage.FOLLOW_A_MEMBER.equals(previousUserMessage.getPrevMessage()) 
							&& !ConstantMessage.COMPLAIN_MEMBER_PASSENGER_OR_DRIVER.equals(previousUserMessage.getPrevMessage()) 
							&& !ConstantMessage.BAN_MEMBER_TO_USE_THE_BOT.equals(previousUserMessage.getPrevMessage()) 
							&& !ConstantMessage.REPORT_TRAFFIC_STATUS.equals(previousUserMessage.getPrevMessage()) 
							&& !ConstantMessage.VERIFY_MEMBER.equals(previousUserMessage.getPrevMessage()) 
							&& CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {

						sendMessage(continuousSaveAndSendMessage(persistenceService, message, replyKeyboardMarkup, southPoolMemberWorkToHome, previousUserMessage, username, PreviousMessage.class));

						if (!MemberValidation.isInfoNotComplete(southPoolMemberWorkToHome)) {
							Work2Home home2Work = new Work2Home(ConstantMessage.WORK2HOME);
							sendMessage(home2Work.proccess(chatId, southPoolMemberWorkToHome));
						}	
					}
					break;
				}
			}

		}

		/*********************
		 * CallBack Commands *
		 *********************/
		if (update.hasCallbackQuery()) {

			SendMessage message = new SendMessage();
			ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
			message.setReplyMarkup(replyKeyboardMarkup);
			replyKeyboardMarkup.setSelective(true);
			replyKeyboardMarkup.setResizeKeyboard(true);
			replyKeyboardMarkup.setOneTimeKeyboard(true);

			String botQuestion;
			String username =  update.getCallbackQuery().getMessage().getChat().getUserName();
			String callData = update.getCallbackQuery().getData();
			long chatId = update.getCallbackQuery().getMessage().getChatId();
			long messageId = update.getCallbackQuery().getMessage().getMessageId();
			message.setChatId(chatId);
			
			CallbackQuery callBackQuery = update.getCallbackQuery();
			AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
			answerCallbackQuery.setCallbackQueryId(callBackQuery.getId());
			answerCallbackQuery.setShowAlert(true);

			MemberValidation.updateUserChatId(persistenceService,chatId,username);

			PreviousMessage previousMessage = persistenceService.getMember(username, PreviousMessage.class);
			SouthPoolMemberHomeToWork southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			SouthPoolMemberWorkToHome southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

			//check if user is a banned member
			if (southPoolMemberHomeToWork != null && southPoolMemberWorkToHome != null) {
				if ("N".equalsIgnoreCase(southPoolMemberHomeToWork.getAllowed()) || "N".equalsIgnoreCase(southPoolMemberWorkToHome.getAllowed())) {
					String x = EmojiParser.parseToUnicode(":x:");
					message.setText(x+ConstantMessage.BANNED_USER);
					sendMessage(message);
					return;
				}	
			}

			Map<String,String> predicatesMap = new HashMap<>();
			InlineKeyboardBuilder builder;
			switch (callData) {

			case CallbackCommands.IOS:
			case CallbackCommands.ANDROID:
			case CallbackCommands.DESKTOP:
				builder = SetTelegramUserName.setTelegramUserName(chatId, callData);
				replaceMessage(chatId, messageId, builder.build());
				break;

			case CallbackCommands.REGISTER:
				if (username == null) {
					sendMessage(RegisterMember.checkNullUserName(update.getCallbackQuery()));
					break;
				}
				builder = RegisterMember.registerUsername(persistenceService, southPoolMemberHomeToWork, southPoolMemberWorkToHome, chatId, username);
				replaceMessage(chatId, messageId, builder.build());
				break;

			case CallbackCommands.SHOW_MEMBER_INFO:
				message.setText(ConstantMessage.ACCOUNT_MESSAGE);
				message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
				sendMessage(message);
				break;

			case CallbackCommands.HOME2WORK:
				MemberValidation.updateUserPreviousMessage(persistenceService, previousMessage, username, CallbackCommands.HOME2WORK);
				if (!MemberValidation.isInfoNotComplete(southPoolMemberHomeToWork)) {
					Home2Work home2Work = new Home2Work(ConstantMessage.HOME2WORK);
					sendMessage(home2Work.proccess(chatId, southPoolMemberHomeToWork));
				}
				else {
					sendMessage(continuousSaveAndSendMessage(persistenceService, message, replyKeyboardMarkup, southPoolMemberHomeToWork, previousMessage, username, PreviousMessage.class));
				}
				break;
			case CallbackCommands.WORK2HOME:
				MemberValidation.updateUserPreviousMessage(persistenceService, previousMessage, username, CallbackCommands.WORK2HOME);
				if (!MemberValidation.isInfoNotComplete(southPoolMemberWorkToHome)) {
					Work2Home home2Work = new Work2Home(ConstantMessage.WORK2HOME);
					sendMessage(home2Work.proccess(chatId, southPoolMemberWorkToHome));
				}
				else {
					sendMessage(continuousSaveAndSendMessage(persistenceService, message, replyKeyboardMarkup, southPoolMemberWorkToHome, previousMessage, username, PreviousMessage.class));
				}
				break;

			case CallbackCommands.DRIVER:
				if (ConstantMessage.ENTER_WHAT_TYPE_OF_USER_YOU_ARE.equals(previousMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousMessage.getTag())) {
					southPoolMemberHomeToWork.setYouAre(ConstantMessage.DRIVER);
					persistenceService.merge(southPoolMemberHomeToWork);
					message.setText("Your account was successfully updated to a DRIVER!");
					sendMessage(message);
					sendMessage(continuousSaveAndSendMessage(persistenceService, message, replyKeyboardMarkup, southPoolMemberHomeToWork, previousMessage, username, PreviousMessage.class));
					if (!MemberValidation.isInfoNotComplete(southPoolMemberHomeToWork)) {
						Home2Work home2Work = new Home2Work(ConstantMessage.HOME2WORK);
						sendMessage(home2Work.proccess(chatId, southPoolMemberHomeToWork));
					}
					break;
				}
				else if (ConstantMessage.ENTER_WHAT_TYPE_OF_USER_YOU_ARE.equals(previousMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousMessage.getTag())) {
					southPoolMemberWorkToHome.setYouAre(ConstantMessage.DRIVER);
					persistenceService.merge(southPoolMemberWorkToHome);
					message.setText("Your account was successfully updated to a DRIVER!");
					sendMessage(message);
					sendMessage(continuousSaveAndSendMessage(persistenceService, message, replyKeyboardMarkup, southPoolMemberWorkToHome, previousMessage, username, PreviousMessage.class));
					if (!MemberValidation.isInfoNotComplete(southPoolMemberWorkToHome)) {
						Work2Home home2Work = new Work2Home(ConstantMessage.WORK2HOME);
						sendMessage(home2Work.proccess(chatId, southPoolMemberWorkToHome));
					}
					break;
				}
				break;

			case CallbackCommands.PASSENGER:	
				if (ConstantMessage.ENTER_WHAT_TYPE_OF_USER_YOU_ARE.equals(previousMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousMessage.getTag())) {
					southPoolMemberHomeToWork.setYouAre(ConstantMessage.PASSENGER);
					persistenceService.merge(southPoolMemberHomeToWork);
					message.setText("Your account was successfully updated to a PASSENGER!");
					sendMessage(message);
					sendMessage(continuousSaveAndSendMessage(persistenceService, message, replyKeyboardMarkup, southPoolMemberHomeToWork, previousMessage, username, PreviousMessage.class));
					if (!MemberValidation.isInfoNotComplete(southPoolMemberHomeToWork)) {
						Home2Work home2Work = new Home2Work(ConstantMessage.HOME2WORK);
						sendMessage(home2Work.proccess(chatId, southPoolMemberHomeToWork));
					}
					break;
				}
				else if (ConstantMessage.ENTER_WHAT_TYPE_OF_USER_YOU_ARE.equals(previousMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousMessage.getTag())) {
					southPoolMemberWorkToHome.setYouAre(ConstantMessage.PASSENGER);
					persistenceService.merge(southPoolMemberWorkToHome);
					message.setText("Your account was successfully updated to a PASSENGER!");
					sendMessage(message);
					sendMessage(continuousSaveAndSendMessage(persistenceService, message, replyKeyboardMarkup, southPoolMemberWorkToHome, previousMessage, username, PreviousMessage.class));
					if (!MemberValidation.isInfoNotComplete(southPoolMemberWorkToHome)) {
						Work2Home home2Work = new Work2Home(ConstantMessage.WORK2HOME);
						sendMessage(home2Work.proccess(chatId, southPoolMemberWorkToHome));
					}
					break;
				}
				break;

			case CallbackCommands.SET_NAME:
			case CallbackCommands.SET_PROFILE_LINK:
			case CallbackCommands.SET_MOBILE:
			case CallbackCommands.SET_CAR_PLATE:
			case CallbackCommands.SET_YOU_ARE:
			case CallbackCommands.SET_PICKUP_LOC:
			case CallbackCommands.SET_DROP_OFF_LOC:
			case CallbackCommands.SET_ROUTE:
			case CallbackCommands.SET_AVAILABLE_SEAT:
			case CallbackCommands.SET_ETA:
			case CallbackCommands.SET_ETD:
			case CallbackCommands.SET_INSTRUCTION:
				sendMessage(UpdateMember.updateMemberInfo(persistenceService, callData, username, chatId, previousMessage, seatKeyBoard, timeKeyBoard, waitingTimeKeyBoard));
				break;

			case CallbackCommands.SEARCH_POST:
				builder = Search.driverOrPassenger(chatId);
				replaceMessage(chatId, messageId, builder.build());
				break;

			case CallbackCommands.POST_REQUEST:

				MemberValidation.updateUserChatId(persistenceService, chatId, username);
				if (CallbackCommands.HOME2WORK.equals(previousMessage.getTag())) {
					southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
					Post post = new Post(ConstantMessage.HOME2WORK);
					replaceMessage(chatId, messageId, post.request(chatId, southPoolMemberHomeToWork));
				}
				else if (CallbackCommands.WORK2HOME.equals(previousMessage.getTag())) {
					southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
					Post post = new Post(ConstantMessage.WORK2HOME);
					replaceMessage(chatId, messageId, post.request(chatId, southPoolMemberWorkToHome));
				}
				break;

			case CallbackCommands.POST_AS_DRIVER:

				southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
				southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

				if (southPoolMemberHomeToWork.getChatId() == null) {
					southPoolMemberHomeToWork.setChatId(String.valueOf(chatId));
					persistenceService.merge(southPoolMemberHomeToWork);	
				}
				if (southPoolMemberWorkToHome.getChatId() == null) {
					southPoolMemberWorkToHome.setChatId(String.valueOf(chatId));
					persistenceService.merge(southPoolMemberWorkToHome);	
				}

				southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
				southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

				if (CallbackCommands.HOME2WORK.equals(previousMessage.getTag())) {
					if (southPoolMemberHomeToWork.getPostCount() == 5) {
						String warning = EmojiParser.parseToUnicode(":warning:");
						answerCallbackQuery.setText(warning+ConstantMessage.RESQUEST_MAX);
						sendMessage(answerCallbackQuery);
						break;
					}
					if (DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						String dateToday = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						String[] etaTime = southPoolMemberHomeToWork.getEta().split(" ");
						String eta = dateToday + " " + etaTime[1];

						String[] etdTime = southPoolMemberHomeToWork.getEta().split(" ");
						String etd = dateToday + " " + etdTime[1];

						southPoolMemberHomeToWork.setEta(eta);
						southPoolMemberHomeToWork.setEtd(etd);
						persistenceService.merge(southPoolMemberHomeToWork);
					}
					if (DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isAfter(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						String dateToday = DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEta()).minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						String[] etaTime = southPoolMemberHomeToWork.getEta().split(" ");
						String eta = dateToday + " " + etaTime[1];

						String[] etdTime = southPoolMemberHomeToWork.getEta().split(" ");
						String etd = dateToday + " " + etdTime[1];

						southPoolMemberHomeToWork.setEta(eta);
						southPoolMemberHomeToWork.setEtd(etd);
						persistenceService.merge(southPoolMemberHomeToWork);
					}
					southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
					postRequestAsDriver(message,predicatesMap,southPoolMemberHomeToWork,username, answerCallbackQuery);



					southPoolMemberHomeToWork.setPostCount(southPoolMemberHomeToWork.getPostCount() == 0 ? 1 : southPoolMemberHomeToWork.getPostCount() + 1);
					persistenceService.merge(southPoolMemberHomeToWork);
				}
				else if (CallbackCommands.WORK2HOME.equals(previousMessage.getTag())) {
					if (southPoolMemberWorkToHome.getPostCount() == 5) {
						String warning = EmojiParser.parseToUnicode(":warning:");
						answerCallbackQuery.setText(warning+ConstantMessage.RESQUEST_MAX);
						sendMessage(answerCallbackQuery);
						break;
					}
					if (DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						String dateToday = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						String[] etaTime = southPoolMemberWorkToHome.getEta().split(" ");
						String eta = dateToday + " " + etaTime[1];

						String[] etdTime = southPoolMemberWorkToHome.getEta().split(" ");
						String etd = dateToday + " " + etdTime[1];

						southPoolMemberWorkToHome.setEta(eta);
						southPoolMemberWorkToHome.setEtd(etd);
						persistenceService.merge(southPoolMemberHomeToWork);
					}
					if (DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isAfter(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						String dateToday = DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEta()).minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						String[] etaTime = southPoolMemberHomeToWork.getEta().split(" ");
						String eta = dateToday + " " + etaTime[1];

						String[] etdTime = southPoolMemberHomeToWork.getEta().split(" ");
						String etd = dateToday + " " + etdTime[1];

						southPoolMemberWorkToHome.setEta(eta);
						southPoolMemberWorkToHome.setEtd(etd);
						persistenceService.merge(southPoolMemberHomeToWork);
					}
					southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
					postRequestAsDriver(message,predicatesMap,southPoolMemberWorkToHome,username, answerCallbackQuery);
					southPoolMemberWorkToHome.setPostCount(southPoolMemberWorkToHome.getPostCount() == 0 ? 1 : southPoolMemberWorkToHome.getPostCount() + 1);
					persistenceService.merge(southPoolMemberWorkToHome);
				}
				break;

			case CallbackCommands.POST_AS_PASSENGER:

				southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
				southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

				if (southPoolMemberHomeToWork.getChatId() == null) {
					southPoolMemberHomeToWork.setChatId(String.valueOf(chatId));
					persistenceService.merge(southPoolMemberHomeToWork);	
				}
				if (southPoolMemberWorkToHome.getChatId() == null) {
					southPoolMemberWorkToHome.setChatId(String.valueOf(chatId));
					persistenceService.merge(southPoolMemberWorkToHome);	
				}

				southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
				southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

				if (CallbackCommands.HOME2WORK.equals(previousMessage.getTag())) {
					if (southPoolMemberHomeToWork.getPostCount() == 5) {
						String warning = EmojiParser.parseToUnicode(":warning:");
						answerCallbackQuery.setText(warning+ConstantMessage.RESQUEST_MAX);
						sendMessage(answerCallbackQuery);
						break;
					}
					if (DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						String dateToday = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						String[] etaTime = southPoolMemberHomeToWork.getEta().split(" ");
						String eta = dateToday + " " + etaTime[1];

						String[] etdTime = southPoolMemberHomeToWork.getEta().split(" ");
						String etd = dateToday + " " + etdTime[1];

						southPoolMemberHomeToWork.setEta(eta);
						southPoolMemberHomeToWork.setEtd(etd);
						persistenceService.merge(southPoolMemberHomeToWork);
					}
					if (DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isAfter(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						String dateToday = DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEta()).minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						String[] etaTime = southPoolMemberHomeToWork.getEta().split(" ");
						String eta = dateToday + " " + etaTime[1];

						String[] etdTime = southPoolMemberHomeToWork.getEta().split(" ");
						String etd = dateToday + " " + etdTime[1];

						southPoolMemberHomeToWork.setEta(eta);
						southPoolMemberHomeToWork.setEtd(etd);
						persistenceService.merge(southPoolMemberHomeToWork);
					}
					southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
					postRequestAsPassenger(message,predicatesMap,southPoolMemberHomeToWork,username, answerCallbackQuery);
					southPoolMemberHomeToWork.setPostCount(southPoolMemberHomeToWork.getPostCount() == 0 ? 1 : southPoolMemberHomeToWork.getPostCount() + 1);
					persistenceService.merge(southPoolMemberHomeToWork);
				}
				else if (CallbackCommands.WORK2HOME.equals(previousMessage.getTag())) {
					if (southPoolMemberWorkToHome.getPostCount() == 5) {
						String warning = EmojiParser.parseToUnicode(":warning:");
						answerCallbackQuery.setText(warning+ConstantMessage.RESQUEST_MAX);
						sendMessage(answerCallbackQuery);
						break;
					}
					if (DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						String dateToday = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						String[] etaTime = southPoolMemberWorkToHome.getEta().split(" ");
						String eta = dateToday + " " + etaTime[1];

						String[] etdTime = southPoolMemberWorkToHome.getEta().split(" ");
						String etd = dateToday + " " + etdTime[1];

						southPoolMemberWorkToHome.setEta(eta);
						southPoolMemberWorkToHome.setEtd(etd);
						persistenceService.merge(southPoolMemberHomeToWork);
					}
					if (DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isAfter(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						String dateToday = DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEta()).minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						String[] etaTime = southPoolMemberHomeToWork.getEta().split(" ");
						String eta = dateToday + " " + etaTime[1];

						String[] etdTime = southPoolMemberHomeToWork.getEta().split(" ");
						String etd = dateToday + " " + etdTime[1];

						southPoolMemberWorkToHome.setEta(eta);
						southPoolMemberWorkToHome.setEtd(etd);
						persistenceService.merge(southPoolMemberHomeToWork);
					}
					southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
					postRequestAsPassenger(message,predicatesMap,southPoolMemberWorkToHome,username, answerCallbackQuery);
					southPoolMemberWorkToHome.setPostCount(southPoolMemberWorkToHome.getPostCount() == 0 ? 1 : southPoolMemberWorkToHome.getPostCount() + 1);
					persistenceService.merge(southPoolMemberWorkToHome);
				}
				break;

			case CallbackCommands.POST_AS_DRIVER_TOMORROW:

				southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
				southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

				if (southPoolMemberHomeToWork.getChatId() == null) {
					southPoolMemberHomeToWork.setChatId(String.valueOf(chatId));
					persistenceService.merge(southPoolMemberHomeToWork);	
				}
				if (southPoolMemberWorkToHome.getChatId() == null) {
					southPoolMemberWorkToHome.setChatId(String.valueOf(chatId));
					persistenceService.merge(southPoolMemberWorkToHome);	
				}

				southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
				southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

				if (CallbackCommands.HOME2WORK.equals(previousMessage.getTag())) {
					if (southPoolMemberHomeToWork.getPostCount() == 5) {
						String warning = EmojiParser.parseToUnicode(":warning:");
						answerCallbackQuery.setText(warning+ConstantMessage.RESQUEST_MAX);
						sendMessage(answerCallbackQuery);
						break;
					}
					if (DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						String dateToday = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						String[] etaTime = southPoolMemberHomeToWork.getEta().split(" ");
						String eta = dateToday + " " + etaTime[1];

						String[] etdTime = southPoolMemberHomeToWork.getEtd().split(" ");
						String etd = dateToday + " " + etdTime[1];

						southPoolMemberHomeToWork.setEta(eta);
						southPoolMemberHomeToWork.setEtd(etd);
						persistenceService.merge(southPoolMemberHomeToWork);
					}
					southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);

					if (LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).isEqual(DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						LocalDateTime localDateTimeETA = DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEta());
						LocalDateTime localDateTimeETD = DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEtd());
						String dateETA = localDateTimeETA.plusDays(1).format(DateUtility.FORMAT_DATETIME);
						String dateETD = localDateTimeETD.plusDays(1).format(DateUtility.FORMAT_DATETIME);
						southPoolMemberHomeToWork.setEta(dateETA);
						southPoolMemberHomeToWork.setEtd(dateETD);
						persistenceService.merge(southPoolMemberHomeToWork);
					}

					southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
					postRequestAsDriver(message,predicatesMap,southPoolMemberHomeToWork,username, answerCallbackQuery);
					southPoolMemberHomeToWork.setPostCount(southPoolMemberHomeToWork.getPostCount() == 0 ? 1 : southPoolMemberHomeToWork.getPostCount() + 1);
					persistenceService.merge(southPoolMemberHomeToWork);
				}
				else if (CallbackCommands.WORK2HOME.equals(previousMessage.getTag())) {
					if (southPoolMemberWorkToHome.getPostCount() == 5) {
						String warning = EmojiParser.parseToUnicode(":warning:");
						answerCallbackQuery.setText(warning+ConstantMessage.RESQUEST_MAX);
						sendMessage(answerCallbackQuery);
						break;
					}
					if (DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						String dateToday = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						String[] etaTime = southPoolMemberWorkToHome.getEta().split(" ");
						String eta = dateToday + " " + etaTime[1];

						String[] etdTime = southPoolMemberWorkToHome.getEtd().split(" ");
						String etd = dateToday + " " + etdTime[1];

						southPoolMemberWorkToHome.setEta(eta);
						southPoolMemberWorkToHome.setEtd(etd);
						persistenceService.merge(southPoolMemberHomeToWork);
					}
					southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

					if (LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).isEqual(DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						LocalDateTime localDateTimeETA = DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEta());
						LocalDateTime localDateTimeETD = DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEtd());
						String dateETA = localDateTimeETA.plusDays(1).format(DateUtility.FORMAT_DATETIME);
						String dateETD = localDateTimeETD.plusDays(1).format(DateUtility.FORMAT_DATETIME);
						southPoolMemberWorkToHome.setEta(dateETA);
						southPoolMemberWorkToHome.setEtd(dateETD);
						persistenceService.merge(southPoolMemberWorkToHome);
					}

					southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
					postRequestAsDriver(message,predicatesMap,southPoolMemberWorkToHome,username, answerCallbackQuery);

					southPoolMemberWorkToHome.setPostCount(southPoolMemberWorkToHome.getPostCount() == 0 ? 1 : southPoolMemberWorkToHome.getPostCount() + 1);
					persistenceService.merge(southPoolMemberWorkToHome);
				}
				break;

			case CallbackCommands.POST_AS_PASSENGER_TOMORROW:

				southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
				southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

				if (southPoolMemberHomeToWork.getChatId() == null) {
					southPoolMemberHomeToWork.setChatId(String.valueOf(chatId));
					persistenceService.merge(southPoolMemberHomeToWork);	
				}
				if (southPoolMemberWorkToHome.getChatId() == null) {
					southPoolMemberWorkToHome.setChatId(String.valueOf(chatId));
					persistenceService.merge(southPoolMemberWorkToHome);	
				}

				southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
				southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

				if (CallbackCommands.HOME2WORK.equals(previousMessage.getTag())) {
					if (southPoolMemberHomeToWork.getPostCount() == 5) {
						String warning = EmojiParser.parseToUnicode(":warning:");
						answerCallbackQuery.setText(warning+ConstantMessage.RESQUEST_MAX);
						sendMessage(answerCallbackQuery);
						break;
					}
					if (DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						String dateToday = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						String[] etaTime = southPoolMemberHomeToWork.getEta().split(" ");
						String eta = dateToday + " " + etaTime[1];

						String[] etdTime = southPoolMemberHomeToWork.getEtd().split(" ");
						String etd = dateToday + " " + etdTime[1];

						southPoolMemberHomeToWork.setEta(eta);
						southPoolMemberHomeToWork.setEtd(etd);
						persistenceService.merge(southPoolMemberHomeToWork);
					}
					southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);

					if (LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).isEqual(DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						LocalDateTime localDateTimeETA = DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEta());
						LocalDateTime localDateTimeETD = DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEtd());
						String dateETA = localDateTimeETA.plusDays(1).format(DateUtility.FORMAT_DATETIME);
						String dateETD = localDateTimeETD.plusDays(1).format(DateUtility.FORMAT_DATETIME);
						southPoolMemberHomeToWork.setEta(dateETA);
						southPoolMemberHomeToWork.setEtd(dateETD);
						persistenceService.merge(southPoolMemberHomeToWork);
					}

					southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
					postRequestAsPassenger(message,predicatesMap,southPoolMemberHomeToWork,username, answerCallbackQuery);
					southPoolMemberWorkToHome.setPostCount(southPoolMemberWorkToHome.getPostCount() == 0 ? 1 : southPoolMemberWorkToHome.getPostCount() + 1);
					persistenceService.merge(southPoolMemberWorkToHome);
				}
				else if (CallbackCommands.WORK2HOME.equals(previousMessage.getTag())) {
					if (southPoolMemberWorkToHome.getPostCount() == 5) {
						String warning = EmojiParser.parseToUnicode(":warning:");
						answerCallbackQuery.setText(warning+ConstantMessage.RESQUEST_MAX);
						sendMessage(answerCallbackQuery);
						break;
					}
					if (DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						String dateToday = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

						String[] etaTime = southPoolMemberWorkToHome.getEta().split(" ");
						String eta = dateToday + " " + etaTime[1];

						String[] etdTime = southPoolMemberWorkToHome.getEtd().split(" ");
						String etd = dateToday + " " + etdTime[1];

						southPoolMemberHomeToWork.setEta(eta);
						southPoolMemberHomeToWork.setEtd(etd);
						persistenceService.merge(southPoolMemberHomeToWork);
					}
					southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

					if (LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0).isEqual(DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
						LocalDateTime localDateTimeETA = DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEta());
						LocalDateTime localDateTimeETD = DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEtd());
						String dateETA = localDateTimeETA.plusDays(1).format(DateUtility.FORMAT_DATETIME);
						String dateETD = localDateTimeETD.plusDays(1).format(DateUtility.FORMAT_DATETIME);
						southPoolMemberWorkToHome.setEta(dateETA);
						southPoolMemberWorkToHome.setEtd(dateETD);
						persistenceService.merge(southPoolMemberWorkToHome);	
					}
					southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
					postRequestAsPassenger(message,predicatesMap,southPoolMemberWorkToHome,username, answerCallbackQuery);
					southPoolMemberWorkToHome.setPostCount(southPoolMemberWorkToHome.getPostCount() == 0 ? 1 : southPoolMemberWorkToHome.getPostCount() + 1);
					persistenceService.merge(southPoolMemberWorkToHome);
				}
				break;
				
			case CallbackCommands.TODAY_DRIVER:
				message.setText(ConstantMessage.SEARCH_DRIVER);
				sendMessage(message);
				predicatesMap = new HashMap<>();
				predicatesMap.put("username", username);
				predicatesMap.put("youAre", CallbackCommands.DRIVER);
				if (CallbackCommands.HOME2WORK.equals(previousMessage.getTag())) {
					List<Member> members = southpoolSearchService.getSouthPoolMemberHomeToWorkMembers(predicatesMap);
					try {
						southpoolSearchService.sendResponseDetailsInTelegram("HOME to WORK DRIVER from " + southPoolMemberHomeToWork.getEta().replaceAll(":", "") + " to " + southPoolMemberHomeToWork.getEtd().replaceAll(":", ""), members, chatId, CallbackCommands.TODAY_DRIVER);
					} catch (IOException | DocumentException e) {
						log.error("{}", e);
					}
				}
				else if (CallbackCommands.WORK2HOME.equals(previousMessage.getTag())) {
					List<Member> members = southpoolSearchService.getSouthPoolMemberWorkToHomeMembers(predicatesMap);
					try {
						southpoolSearchService.sendResponseDetailsInTelegram("WORK to HOME DRIVER from " + southPoolMemberWorkToHome.getEta().replaceAll(":", "") + " to " + southPoolMemberWorkToHome.getEtd().replaceAll(":", ""), members, chatId, CallbackCommands.TODAY_DRIVER);
					} catch (IOException | DocumentException e) {
						log.error("{}", e);
					}
				}
				break;

			case CallbackCommands.TODAY_PASSENGER:
				message.setText(ConstantMessage.SEARCH_PASSENGER);
				sendMessage(message);
				predicatesMap = new HashMap<>();
				predicatesMap.put("username", username);
				predicatesMap.put("youAre", CallbackCommands.PASSENGER);
				if (CallbackCommands.HOME2WORK.equals(previousMessage.getTag())) {
					List<Member> members = southpoolSearchService.getSouthPoolMemberHomeToWorkMembers(predicatesMap);
					try {
						southpoolSearchService.sendResponseDetailsInTelegram("HOME to WORK PASSENGER from " + southPoolMemberHomeToWork.getEta().replaceAll(":", "") + " to " + southPoolMemberHomeToWork.getEtd().replaceAll(":", ""), members, chatId, CallbackCommands.TODAY_PASSENGER);
					} catch (IOException | DocumentException e) {
						log.error("{}", e);
					}
				}
				else if (CallbackCommands.WORK2HOME.equals(previousMessage.getTag())) {
					List<Member> members = southpoolSearchService.getSouthPoolMemberWorkToHomeMembers(predicatesMap);
					try {
						southpoolSearchService.sendResponseDetailsInTelegram("WORK to HOME PASSENGER from " + southPoolMemberWorkToHome.getEta().replaceAll(":", "") + " to " + southPoolMemberWorkToHome.getEtd().replaceAll(":", ""), members, chatId, CallbackCommands.TODAY_PASSENGER);
					} catch (IOException | DocumentException e) {
						log.error("{}", e);
					}
				}
				break;

			case CallbackCommands.VERIFY_MEMBER:
				botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.VERIFY_MEMBER, previousMessage, username, PreviousMessage.class);
				message.setText(botQuestion);
				sendMessage(message);
				break;

			case CallbackCommands.REPORT_TRAFFIC:
				botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.REPORT_TRAFFIC_STATUS, previousMessage, username, PreviousMessage.class);
				message.setText(botQuestion);
				sendMessage(message);
				break;

			case CallbackCommands.BAN_MEMBER:
				if ("Y".equalsIgnoreCase(southPoolMemberHomeToWork.getAdmin()) && "Y".equalsIgnoreCase(southPoolMemberWorkToHome.getAdmin())) {
					botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.BAN_MEMBER_TO_USE_THE_BOT, previousMessage, username, PreviousMessage.class);
					message.setText(botQuestion);
					sendMessage(message);	
				}
				else {
					String x = EmojiParser.parseToUnicode(":x:");
					answerCallbackQuery.setText(x+ConstantMessage.ADMIN_ONLY);
					sendMessage(answerCallbackQuery);
				}
				break;

			case CallbackCommands.COMPLAIN_MEMBER:
				botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.COMPLAIN_MEMBER_PASSENGER_OR_DRIVER, previousMessage, username, PreviousMessage.class);
				message.setText(botQuestion);
				sendMessage(message);
				break;

			case CallbackCommands.FOLLOW_MEMBER:
				if (southPoolMemberHomeToWork != null && southPoolMemberWorkToHome != null) {
					
					if (southPoolMemberHomeToWork.getChatId() == null) {
						southPoolMemberHomeToWork.setChatId(String.valueOf(chatId));
						persistenceService.merge(southPoolMemberHomeToWork);	
					}
					if (southPoolMemberWorkToHome.getChatId() == null) {
						southPoolMemberWorkToHome.setChatId(String.valueOf(chatId));
						persistenceService.merge(southPoolMemberWorkToHome);	
					}			
				}
				botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.FOLLOW_A_MEMBER, previousMessage, username, PreviousMessage.class);
				message.setText(botQuestion);
				sendMessage(message);
				break;
				
			case CallbackCommands.HELP:
				message.setText(ConstantMessage.showThankYou());
				sendMessage(message);
				break;
				
			case CallbackCommands.BOT_UPDATE:
				try {
					southPoolService.sendMessage(ConstantMessage.showThankUpdate(), southPoolSettings.getGroupChatId(), southPoolSettings);
				} catch (UnsupportedEncodingException e) {
					log.error("",e);
				}
				break;
				
			default:
				message.setText("Invalid command!");
				sendMessage(message);
				break;
			}
		}


	}

	@Override
	public String getBotUsername() {
		return southPoolSettings.getTelegramBotUsername();
	}

	@Override
	public String getBotToken() {
		return southPoolSettings.getTelegramBotToken();
	}

	private SendMessage continuousSaveAndSendMessage(PersistenceService persistenceService, SendMessage message, ReplyKeyboardMarkup replyKeyboardMarkup, Member member, PreviousMessage previousMessage, String username, @SuppressWarnings("rawtypes") Class clazz) {
		String botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.showInfoToUpdateNext(member), previousMessage, username, clazz);
		message.setText(botQuestion);
		if(ConstantMessage.ENTER_WHAT_TYPE_OF_USER_YOU_ARE.equals(botQuestion)) {
			message.setReplyMarkup(ConstantMessage.shownOptionsForDriverOrPassenger());
		}
		else if(ConstantMessage.ENTER_SEAT.equals(botQuestion)) {
			replyKeyboardMarkup.setKeyboard(seatKeyBoard);
		}
		else if (ConstantMessage.ENTER_ETA.equals(botQuestion)) {
			replyKeyboardMarkup.setKeyboard(timeKeyBoard);
		}
		else if (ConstantMessage.ENTER_WAITING.equals(botQuestion)) {
			replyKeyboardMarkup.setKeyboard(waitingTimeKeyBoard);
		}
		return message;
	}



	protected void sendMessage(SendMessage message) {
		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	protected void replaceMessage(long chatId, long messageId, SendMessage message) {
		EditMessageText newMessage = new EditMessageText()
				.setChatId(chatId)
				.setMessageId(Math.toIntExact(messageId))
				.setText(message.getText())
				.setParseMode("HTML")
				.setReplyMarkup((InlineKeyboardMarkup) message.getReplyMarkup());
		try {
			execute(newMessage);
		} catch (TelegramApiException e) {
			log.error("{}",e);
		}
	}

	protected void sendMessage(AnswerCallbackQuery answerCallbackQuery) {
		try {
			execute(answerCallbackQuery);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	private boolean postRequestAsDriver(SendMessage message, Map<String,String> predicatesMap, Member member, String username, AnswerCallbackQuery answerCallbackQuery) {
		if(MemberValidation.isInfoNotComplete(member)) {
			message.setText("Sorry, you are not allowed to post your request as a DRIVER because your information is not yet complete.\n\n" + ConstantMessage.showInfoToUpdateNext(member));
			sendMessage(message);
			return false;
		}
		else if (ConstantMessage.PASSENGER.equals(member.getYouAre())) {
			message.setText("Sorry, you are not allowed to post your request as a DRIVER because your account is registered as a PASSENGER. Kindly update your account to PASSENGER first. Thank you!\n\n");
			sendMessage(message);
			return false;
		}
		else {
			try {
				southPoolService.sendMessage(ConstantMessage.showMyInformation(member), southPoolSettings.getGroupChatId(), southPoolSettings);
			} catch (UnsupportedEncodingException e) {
				log.error("",e);
			}

			String ok = EmojiParser.parseToUnicode(":white_check_mark:");
			answerCallbackQuery.setText(ok+ConstantMessage.RESQUEST_POSTED);
			sendMessage(answerCallbackQuery);

			message.setText(ConstantMessage.ACCOUNT_MESSAGE);
			message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
			sendMessage(message);

			//notify the followers
			Map<String,String> uniqueConstraintNameValueMap = new HashMap<>();
			uniqueConstraintNameValueMap.put("username", username);
			List<Followers> followers = persistenceService.getFolowerBy(uniqueConstraintNameValueMap, Followers.class);
			for (Followers follower : followers) {
				if (!"Y".equals(follower.getActive())) {
					continue;
				}
				try {
					southPoolService.sendMessageToFollowers(ConstantMessage.showOrPostMyInformationToFollower(member), follower.getChatId(), southPoolSettings);
				} catch (UnsupportedEncodingException e) {
					log.error("",e);
				}
			}

			return true;
		}
	}
	
	private boolean postRequestAsPassenger(SendMessage message, Map<String,String> predicatesMap, Member member, String username, AnswerCallbackQuery answerCallbackQuery) {
		if(MemberValidation.isInfoNotComplete(member)) {
			message.setText("Sorry, you are not allowed to post your request as a PASSENGER because your information is not yet complete.\n\n" + ConstantMessage.showInfoToUpdateNext(member));
			sendMessage(message);
			return false;
		}
		else if (ConstantMessage.DRIVER.equals(member.getYouAre())) {
			message.setText("Sorry, you are not allowed to post your request as a PASSENGER because your account is registered as a DRIVER. Kindly update your account to DRIVER first. Thank you!\n\n");
			sendMessage(message);
			return false;
		}
		else {
			try {
				southPoolService.sendMessage(ConstantMessage.showMyInformation(member), southPoolSettings.getGroupChatId(), southPoolSettings);
			} catch (UnsupportedEncodingException e) {
				log.error("",e);
			}

			String ok = EmojiParser.parseToUnicode(":white_check_mark:");
			answerCallbackQuery.setText(ok+ConstantMessage.RESQUEST_POSTED);
			sendMessage(answerCallbackQuery);

			message.setText(ConstantMessage.ACCOUNT_MESSAGE);
			message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
			sendMessage(message);
			
			//notify the followers
			Map<String,String> uniqueConstraintNameValueMap = new HashMap<>();
			uniqueConstraintNameValueMap.put("username", username);
			List<Followers> followers = persistenceService.getFolowerBy(uniqueConstraintNameValueMap, Followers.class);
			for (Followers follower : followers) {
				if (!"Y".equals(follower.getActive())) {
					continue;
				}
				try {
					southPoolService.sendMessageToFollowers(ConstantMessage.showOrPostMyInformationToFollower(member), follower.getChatId(), southPoolSettings);
				} catch (UnsupportedEncodingException e) {
					log.error("",e);
				}
			}
			return true;
		}
	}
}
