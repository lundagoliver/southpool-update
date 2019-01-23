package com.systems.telegram.bot.southpool.controller.search;

import com.systems.telegram.bot.southpool.utility.callback.CallbackCommands;
import com.systems.telegram.bot.southpool.utility.menu.InlineKeyboardBuilder;
import com.systems.telegram.bot.southpool.utility.menu.MenuManager;
import com.vdurmont.emoji.EmojiParser;

public class Search {

	private Search(){}
	
	public static InlineKeyboardBuilder driverOrPassenger(long chatId) {
		MenuManager menuManager = new MenuManager();
		menuManager.setColumnsCount(1);
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":taxi: :red_car: :mag: ") + " Search Today's Driver", CallbackCommands.TODAY_DRIVER);
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":male_office_worker: :woman_office_worker: :mag: ") + " Search Today's Passenger", CallbackCommands.TODAY_PASSENGER);
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":white_check_mark: ") + "Verify Member", CallbackCommands.VERIFY_MEMBER);
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":+1: ") + "Follow a Member", CallbackCommands.FOLLOW_MEMBER);
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":information_source: ") + "My Info", CallbackCommands.SHOW_MEMBER_INFO);
		menuManager.init();
		InlineKeyboardBuilder builder = menuManager.createMenuForPage(0, true);
		builder.setChatId(chatId);
		builder.setChatId(chatId).setText("Search for Driver or Passenger \nPlease Choose action:");
		return builder;
	}
}
