package com.systems.telegram.bot.southpool.controller.update;

import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import com.systems.telegram.bot.southpool.entities.PreviousMessage;
import com.systems.telegram.bot.southpool.persistence.service.PersistenceService;
import com.systems.telegram.bot.southpool.utility.MemberValidation;
import com.systems.telegram.bot.southpool.utility.callback.CallbackCommands;
import com.systems.telegram.bot.southpool.utility.message.ConstantMessage;

public class UpdateMember {

	private UpdateMember (){}

	public static SendMessage updateMemberInfo(
			PersistenceService persistenceService, 
			String callData, 
			String username, 
			long chatId, 
			PreviousMessage previousMessage,
			List<KeyboardRow> seatKeyBoard,
			List<KeyboardRow> timeKeyBoard,
			List<KeyboardRow> waitingTimeKeyBoard
			) 
	{
		SendMessage message = new SendMessage();
		message.setChatId(chatId);
		String botQuestion;
		switch (callData) {
		case CallbackCommands.SET_NAME:

			botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.ENTER_YOUR_NAME, previousMessage, username, PreviousMessage.class);
			message.setText(botQuestion);
			return message;

		case CallbackCommands.SET_PROFILE_LINK:
			botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.ENTER_FACEBOOK_PROFILE_LINK, previousMessage, username, PreviousMessage.class);
			message.setText(botQuestion);
			return message;

		case CallbackCommands.SET_MOBILE:
			botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.ENTER_MOBILE_NUMBER, previousMessage, username, PreviousMessage.class);
			message.setText(botQuestion);
			return message;

		case CallbackCommands.SET_CAR_PLATE:
			botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.ENTER_CAR_PLATE_NUMBER, previousMessage, username, PreviousMessage.class);
			message.setText(botQuestion);
			return message;

		case CallbackCommands.SET_YOU_ARE:
			botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.ENTER_WHAT_TYPE_OF_USER_YOU_ARE, previousMessage, username, PreviousMessage.class);
			message.setText(botQuestion);
			message.setReplyMarkup(ConstantMessage.shownOptionsForDriverOrPassenger());
			return message;

		case CallbackCommands.SET_PICKUP_LOC:
			botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.ENTER_PICK_UP_LOCATION, previousMessage, username, PreviousMessage.class);
			message.setText(botQuestion);
			return message;

		case CallbackCommands.SET_DROP_OFF_LOC:
			botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.ENTER_DROP_OFF_LOCATION, previousMessage, username, PreviousMessage.class);
			message.setText(botQuestion);
			return message;

		case CallbackCommands.SET_ROUTE:
			botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.ENTER_ROUTE, previousMessage, username, PreviousMessage.class);
			message.setText(botQuestion);
			return message;

		case CallbackCommands.SET_AVAILABLE_SEAT:
			SendMessage seatMessage = new SendMessage();
			seatMessage.setChatId(chatId);
			ReplyKeyboardMarkup seatReplyKeyboardMarkup = new ReplyKeyboardMarkup();
			seatMessage.setReplyMarkup(seatReplyKeyboardMarkup);
			seatReplyKeyboardMarkup.setSelective(true);
			seatReplyKeyboardMarkup.setResizeKeyboard(true);
			seatReplyKeyboardMarkup.setOneTimeKeyboard(true);
			botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.ENTER_SEAT, previousMessage, username, PreviousMessage.class);
			seatMessage.setText(botQuestion);
			seatReplyKeyboardMarkup.setKeyboard(seatKeyBoard);
			return seatMessage;

		case CallbackCommands.SET_ETA:
			SendMessage etaMessage = new SendMessage();
			etaMessage.setChatId(chatId);
			ReplyKeyboardMarkup etaReplyKeyboardMarkup = new ReplyKeyboardMarkup();
			etaMessage.setReplyMarkup(etaReplyKeyboardMarkup);
			etaReplyKeyboardMarkup.setSelective(true);
			etaReplyKeyboardMarkup.setResizeKeyboard(true);
			etaReplyKeyboardMarkup.setOneTimeKeyboard(true);
			botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.ENTER_ETA, previousMessage, username, PreviousMessage.class);
			etaMessage.setText(botQuestion);
			etaReplyKeyboardMarkup.setKeyboard(timeKeyBoard);
			return etaMessage;

		case CallbackCommands.SET_ETD:
			SendMessage etdMessage = new SendMessage();
			etdMessage.setChatId(chatId);
			ReplyKeyboardMarkup etdReplyKeyboardMarkup = new ReplyKeyboardMarkup();
			etdMessage.setReplyMarkup(etdReplyKeyboardMarkup);
			etdReplyKeyboardMarkup.setSelective(true);
			etdReplyKeyboardMarkup.setResizeKeyboard(true);
			etdReplyKeyboardMarkup.setOneTimeKeyboard(true);
			botQuestion = MemberValidation.saveAndSendMessage(persistenceService, ConstantMessage.ENTER_WAITING, previousMessage, username, PreviousMessage.class);
			etdMessage.setText(botQuestion);
			etdReplyKeyboardMarkup.setKeyboard(waitingTimeKeyBoard);
			return etdMessage;

		case CallbackCommands.SET_INSTRUCTION:
			botQuestion = MemberValidation.saveAndSendMessage(persistenceService,ConstantMessage.ENTER_SPECIAL_MESSAGE, previousMessage, username, PreviousMessage.class);
			message.setText(botQuestion);
			return message;
		}
		return message;
	}
}
