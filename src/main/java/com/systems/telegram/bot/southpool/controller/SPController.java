package com.systems.telegram.bot.southpool.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.systems.telegram.bot.southpool.controller.register.RegisterMember;
import com.systems.telegram.bot.southpool.controller.setusername.SetTelegramUserName;
import com.systems.telegram.bot.southpool.entities.PreviousMessage;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberHomeToWork;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberWorkToHome;
import com.systems.telegram.bot.southpool.persistence.service.PersistenceService;
import com.systems.telegram.bot.southpool.props.SouthPoolSettings;
import com.systems.telegram.bot.southpool.service.SouthPoolService;
import com.systems.telegram.bot.southpool.service.SouthpoolSearchService;
import com.systems.telegram.bot.southpool.utility.MemberValidation;
import com.systems.telegram.bot.southpool.utility.callback.CallbackCommands;
import com.systems.telegram.bot.southpool.utility.menu.InlineKeyboardBuilder;
import com.systems.telegram.bot.southpool.utility.message.ConstantMessage;

@Component
public class SPController extends TelegramLongPollingBot {
	
	private static final Log log = LogFactory.getLog(TelegramLongPollingBot.class);
	
	private SouthPoolSettings southPoolSettings;
	private PersistenceService persistenceService;	
	private SouthPoolService southPoolService;
	private SouthpoolSearchService southpoolSearchService;
	
	public SPController(SouthPoolSettings southPoolSettings, PersistenceService persistenceService,
			SouthPoolService southPoolService, SouthpoolSearchService southpoolSearchService) {
		super();
		this.southPoolSettings = southPoolSettings;
		this.persistenceService = persistenceService;
		this.southPoolService = southPoolService;
		this.southpoolSearchService = southpoolSearchService;
	}

	@Override
	public void onUpdateReceived(Update update) {
		
		if (update.hasMessage() && update.getMessage().hasText()) {
			
			String username = update.getMessage().getChat().getUserName();
			String messageText = update.getMessage().getText();
			long chatId = update.getMessage().getChatId();
			long messageId = update.getMessage().getMessageId();
			
			log.info("messageId : " + messageId);
			
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
			
			//Check if user is a registered member. If not, ask the user to register or exit the application.
			SouthPoolMemberHomeToWork southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			SouthPoolMemberWorkToHome southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			
			InlineKeyboardBuilder builder;
			switch  (messageText) {
			case ConstantMessage.START:
				message.setText(ConstantMessage.ACCOUNT_MESSAGE);
				message.setReplyMarkup(ConstantMessage.shownOptionsForWorkAndHomeInfo());
				sendMessage(message);
				break;

			default :
				break;
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
			message = new SendMessage().setChatId(chatId);
				
			PreviousMessage previousMessage = persistenceService.getMember(username, PreviousMessage.class);
			SouthPoolMemberHomeToWork southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			SouthPoolMemberWorkToHome southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			
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
					AnswerCallbackQuery answerCallbackQuery = RegisterMember.checkNullUserName(update.getCallbackQuery());
					sendMessage(answerCallbackQuery);
					break;
				}
				builder = RegisterMember.registerUsername(persistenceService, southPoolMemberHomeToWork, southPoolMemberWorkToHome, chatId, username);
				replaceMessage(chatId, messageId, builder.build());
				break;
				
			case CallbackCommands.HOME2WORK:
				if (previousMessage == null) {
					previousMessage = new PreviousMessage();
					previousMessage.setUsername(username);
					previousMessage.setTag(CallbackCommands.HOME2WORK);
					persistenceService.persist(previousMessage);
				}
				if (!MemberValidation.isInfoNotComplete(southPoolMemberHomeToWork)) {
					
				}
				else {
					
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
}
