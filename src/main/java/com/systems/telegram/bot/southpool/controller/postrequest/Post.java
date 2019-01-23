package com.systems.telegram.bot.southpool.controller.postrequest;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.systems.telegram.bot.southpool.entities.Member;
import com.systems.telegram.bot.southpool.utility.callback.CallbackCommands;
import com.systems.telegram.bot.southpool.utility.menu.InlineKeyboardBuilder;
import com.systems.telegram.bot.southpool.utility.menu.MenuManager;
import com.systems.telegram.bot.southpool.utility.message.ConstantMessage;
import com.vdurmont.emoji.EmojiParser;

public class Post {

	String account;

	public Post(String account) {
		super();
		this.account = account;
	}
	
	public SendMessage request(long chatId, Member member) {
		MenuManager menuManager = new MenuManager();
		menuManager.setColumnsCount(1);
		if (ConstantMessage.DRIVER.equals(member.getYouAre())) {
			menuManager.addMenuItem(EmojiParser.parseToUnicode(":taxi: ") + "Post as Driver Today", CallbackCommands.POST_AS_DRIVER);
			menuManager.addMenuItem(EmojiParser.parseToUnicode(":taxi: ") + "Post as Driver Tomorrow", CallbackCommands.POST_AS_DRIVER_TOMORROW);
		}
		else {
			menuManager.addMenuItem(EmojiParser.parseToUnicode(":male_office_worker: ") +"Post as Passenger Today", CallbackCommands.POST_AS_PASSENGER);
			menuManager.addMenuItem(EmojiParser.parseToUnicode(":male_office_worker: ") +" Post as Passenger Tomorrow", CallbackCommands.POST_AS_PASSENGER_TOMORROW);	
		}
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":information_source: ") + "My info", CallbackCommands.SHOW_MEMBER_INFO);
		menuManager.init();
		InlineKeyboardBuilder builder = menuManager.createMenuForPage(0, true);
		builder.setParse("HTML")
		.setChatId(chatId)
		.setText("<b>" + this.account +"</b>\n\n"+ ConstantMessage.showMyInformation(member)+ConstantMessage.PLEASE_CHOOSE_ACTION);
		return builder.build();
	}
}
