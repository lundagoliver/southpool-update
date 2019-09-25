package com.systems.telegram.bot.southpool.controller.setusername;

import com.systems.telegram.bot.southpool.utility.callback.CallbackCommands;
import com.systems.telegram.bot.southpool.utility.menu.InlineKeyboardBuilder;
import com.systems.telegram.bot.southpool.utility.menu.MenuManager;
import com.systems.telegram.bot.southpool.utility.message.ConstantMessage;

public class SetTelegramUserName {
	
	private SetTelegramUserName(){}
	
	public static InlineKeyboardBuilder setTelegramUserName(long chatId, String callData ) {
	
		MenuManager menuManager = new MenuManager();
		menuManager.setColumnsCount(3);
		menuManager.addMenuItem(ConstantMessage.IOS, CallbackCommands.IOS,"https://www.wikihow.com/Know-a-Chat-ID-on-Telegram-on-iPhone-or-iPad");
		menuManager.addMenuItem(ConstantMessage.ANDROID, CallbackCommands.ANDROID,"https://www.wikihow.com/Change-Your-Name-on-Telegram-on-Android");
		menuManager.addMenuItem(ConstantMessage.DESKTOP, CallbackCommands.DESKTOP,"https://wavelet.atlassian.net/wiki/spaces/WMS/pages/21365027/How+to+change+username+in+Telegram");
		menuManager.addMenuItem(ConstantMessage.REGISTER, CallbackCommands.REGISTER,"");
		menuManager.init();
		InlineKeyboardBuilder builder = menuManager.createMenuForPage(0, true);
		builder.setChatId(chatId);
		if(CallbackCommands.IOS.equals(callData)) {
			builder.setText(ConstantMessage.IOS_LINK_MESSAGE + ConstantMessage.SETUP_USERNAME_DONE_MESSAGE);
		}
		else if (CallbackCommands.ANDROID.equals(callData)) {
			builder.setText(ConstantMessage.ANDROID_LINK_MESSAGE + ConstantMessage.SETUP_USERNAME_DONE_MESSAGE);
		}
		else if (CallbackCommands.DESKTOP.equals(callData)) {
			builder.setText(ConstantMessage.DESKTOP_LINK_MESSAGE + ConstantMessage.SETUP_USERNAME_DONE_MESSAGE);
		}
		return builder;
	}

}
