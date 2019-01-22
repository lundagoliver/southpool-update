package com.systems.telegram.bot.southpool.utility.message;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.systems.telegram.bot.southpool.utility.callback.CallbackCommands;

public class ConstantMessage {

	public static final String START = "/start";
	public static final String IOS = "IOS";
	public static final String ANDROID = "ANDROID";
	public static final String DESKTOP = "DESKTOP";
	
	public static final String REGISTER = "REGISTER";
	
	public static final String HOME2WORK = "HOME to WORK";
	public static final String WORK2HOME = "WORK to HOME";
	
	public static final String ACCOUNT_MESSAGE = "Please select which account you want to use:";
	
	public static final String SET_USERNAME_MESSAGE_POP_UP = "Sorry, but you need to set your username first before you can use this service.\n\n";
	public static final String SET_USERNAME_MESSAGE = "Sorry, but you need to set your username first before you can use this service.\n\n Please choose action:";
	
	public static final String SETUP_USERNAME_DONE_MESSAGE = " \nThen once you have done setting your telegram user name. You can proceed to member registration by clicking \"REGISTER\" button below.";
	public static final String IOS_LINK_MESSAGE = "Kindly click this link to see the steps on how to set a username in android device >> https://www.wikihow.com/Change-Your-Name-on-Telegram-on-Android \n";
	public static final String ANDROID_LINK_MESSAGE = "Kindly click this link to see the steps on how to set a username in ios device >> https://www.wikihow.com/Know-a-Chat-ID-on-Telegram-on-iPhone-or-iPad \n";
	public static final String DESKTOP_LINK_MESSAGE = "Kindly click this link to see the steps on how to set a username in desktop >> https://wavelet.atlassian.net/wiki/spaces/WMS/pages/21365027/How+to+change+username+in+Telegram \n";
	
	private ConstantMessage(){}
	
	public static InlineKeyboardMarkup shownOptionsForSettingUserName() {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		rowInline.add(new InlineKeyboardButton().setText(IOS).setCallbackData(CallbackCommands.IOS));
		rowInline.add(new InlineKeyboardButton().setText(ANDROID).setCallbackData(CallbackCommands.ANDROID));
		rowInline.add(new InlineKeyboardButton().setText(DESKTOP).setCallbackData(CallbackCommands.DESKTOP));
		rowsInline.add(rowInline);
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}
	
	public static InlineKeyboardMarkup shownOptionsForWorkAndHomeInfo() {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		rowInline.add(new InlineKeyboardButton().setText(HOME2WORK).setCallbackData(CallbackCommands.HOME2WORK));
		rowInline.add(new InlineKeyboardButton().setText(WORK2HOME).setCallbackData(CallbackCommands.WORK2HOME));
		rowsInline.add(rowInline);
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}
}
