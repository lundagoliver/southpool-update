package com.systems.telegram.bot.southpool.controller.register;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import com.systems.telegram.bot.southpool.entities.SouthPoolMemberHomeToWork;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberWorkToHome;
import com.systems.telegram.bot.southpool.persistence.service.PersistenceService;
import com.systems.telegram.bot.southpool.utility.callback.CallbackCommands;
import com.systems.telegram.bot.southpool.utility.menu.InlineKeyboardBuilder;
import com.systems.telegram.bot.southpool.utility.menu.MenuManager;
import com.systems.telegram.bot.southpool.utility.message.ConstantMessage;

public class RegisterMember {

	private RegisterMember(){}
	
	public static AnswerCallbackQuery checkNullUserName(CallbackQuery callBackQuery) {
		AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
		answerCallbackQuery.setCallbackQueryId(callBackQuery.getId());
		answerCallbackQuery.setShowAlert(true);
		answerCallbackQuery.setText(ConstantMessage.SET_USERNAME_MESSAGE_POP_UP);
		return answerCallbackQuery;
	}
	
	
	public static InlineKeyboardBuilder registerUsername(PersistenceService persistenceService, SouthPoolMemberHomeToWork southPoolMemberHomeToWork, SouthPoolMemberWorkToHome southPoolMemberWorkToHome, long chatId, String username) {
		if (southPoolMemberHomeToWork == null) {
			southPoolMemberHomeToWork = new SouthPoolMemberHomeToWork();
			southPoolMemberHomeToWork.setUsername(username);
			southPoolMemberHomeToWork.setChatId(String.valueOf(chatId));
			southPoolMemberHomeToWork.setPostCount(0);
			southPoolMemberHomeToWork.setAllowed("Y");
			southPoolMemberHomeToWork.setAdmin("N");
			southPoolMemberHomeToWork.setChatId(String.valueOf(chatId));
			persistenceService.persist(southPoolMemberHomeToWork);	
		}
		if (southPoolMemberWorkToHome == null) {
			southPoolMemberWorkToHome = new SouthPoolMemberWorkToHome();
			southPoolMemberWorkToHome.setUsername(username);
			southPoolMemberWorkToHome.setPostCount(0);
			southPoolMemberWorkToHome.setChatId(String.valueOf(chatId));
			southPoolMemberWorkToHome.setAllowed("Y");
			southPoolMemberWorkToHome.setAdmin("N");
			southPoolMemberWorkToHome.setChatId(String.valueOf(chatId));
			persistenceService.persist(southPoolMemberWorkToHome);	
		}
		
		MenuManager menuManager = new MenuManager();
		menuManager.setColumnsCount(1);
		menuManager.addMenuItem(ConstantMessage.HOME2WORK, CallbackCommands.HOME2WORK);
		menuManager.addMenuItem(ConstantMessage.WORK2HOME, CallbackCommands.WORK2HOME);
		menuManager.init();
		InlineKeyboardBuilder builder = menuManager.createMenuForPage(0, true);
		builder.setChatId(chatId);
		builder.setText(ConstantMessage.ACCOUNT_MESSAGE);
		
		return builder;
	}
}
