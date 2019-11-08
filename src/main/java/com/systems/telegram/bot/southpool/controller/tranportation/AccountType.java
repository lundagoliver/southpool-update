package com.systems.telegram.bot.southpool.controller.tranportation;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import com.systems.telegram.bot.southpool.entities.Member;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberWorkToHome;
import com.systems.telegram.bot.southpool.persistence.service.PersistenceService;
import com.systems.telegram.bot.southpool.utility.MemberValidation;
import com.systems.telegram.bot.southpool.utility.callback.CallbackCommands;
import com.systems.telegram.bot.southpool.utility.menu.InlineKeyboardBuilder;
import com.systems.telegram.bot.southpool.utility.menu.MenuManager;
import com.systems.telegram.bot.southpool.utility.message.ConstantMessage;
import com.vdurmont.emoji.EmojiParser;

public abstract class AccountType {

	String account;

	public AccountType(String account) {
		super();
		this.account = account;
	}

	public SendMessage proccess(long chatId, Member member,PersistenceService persistenceService) {
		MenuManager menuManager = new MenuManager();
		menuManager.setColumnsCount(2);
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":information_source: ") + "My Accounts", CallbackCommands.SHOW_MEMBER_INFO,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen: ") + "Update My Info", CallbackCommands.UPDATE_MEMBER_INFO,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":bird: ") + "Request - " + (5 - member.getPostCount()), CallbackCommands.POST_REQUEST,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":mag_right: ") + "Search", CallbackCommands.SEARCH_POST,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":white_check_mark: ") + "Verify a Member", CallbackCommands.VERIFY_MEMBER,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":traffic_light: ") + "Report Traffic Status", CallbackCommands.REPORT_TRAFFIC,"");
		if("Y".equals(member.getAdmin())) {
			menuManager.addMenuItem(EmojiParser.parseToUnicode(":no_mobile_phones: ") + "Ban a Member", CallbackCommands.BAN_MEMBER,"");	
			menuManager.addMenuItem(EmojiParser.parseToUnicode(":no_mobile_phones: ") + "Reset Account", CallbackCommands.RESET_ACCOUNT,"");
			menuManager.addMenuItem(EmojiParser.parseToUnicode(":no_mobile_phones: ") + "Reset Request", CallbackCommands.RESET_REQUEST,"");
		}
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":interrobang: ") + "Report to Admins", CallbackCommands.COMPLAIN_MEMBER,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":+1: ") + "Follow a Member", CallbackCommands.FOLLOW_MEMBER,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":star: ") + "My Followers", CallbackCommands.MY_FOLOWERS,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":triangular_flag_on_post: ") + "My Location", CallbackCommands.MY_LOCATION,"");
		if (!MemberValidation.isInfoNotComplete(member)) {
			menuManager.addMenuItem(EmojiParser.parseToUnicode(":key: ") + "Join SP Group", CallbackCommands.JOIN_SP_GROUP,"https://t.me/joinchat/DGZqOhULGKGwUnquDIyQJg");
		}
		
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":star: ") + "Send Star To a Member", CallbackCommands.GIVE_STAR,"");
		if (!MemberValidation.isInfoNotComplete(member) && ("DRIVER".equals(member.getYouAre()))) {
			menuManager.addMenuItem(EmojiParser.parseToUnicode(":car: ") + "Join Southpool Driver's Lounge", CallbackCommands.JOIN_SP_DRIVERS_GROUP,"https://t.me/joinchat/DGZqOlW6iga9Lkn-hwk-Zw");	
		}
		
		if ("N".equals(member.getCreatePage())) {
			menuManager.addMenuItem(EmojiParser.parseToUnicode(":file_folder: ") + "Create Profile", CallbackCommands.CREATE_PROFILE,"");	
		}
		else {
			menuManager.addMenuItem(EmojiParser.parseToUnicode(":file_folder: ") + "Publish Profile", CallbackCommands.PUBLISH_PROFILE,"");
		}
		
		
		menuManager.init();
		InlineKeyboardBuilder builder = menuManager.createMenuForPage(0, true);
		builder.setParse("HTML")
		.setChatId(chatId)
		.setText(ConstantMessage.showMyInformation(member,this.account, persistenceService)+ConstantMessage.PLEASE_CHOOSE_ACTION);
		return builder.build();
	}
	
	public SendMessage updateInformation(long chatId, Member member, PersistenceService persistenceService) {
		MenuManager menuManager = new MenuManager();
		menuManager.setColumnsCount(2);
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen: ") + "Edit You Are", CallbackCommands.SET_YOU_ARE,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen: ") + "Edit Name", CallbackCommands.SET_NAME,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen: ") + "Edit Link", CallbackCommands.SET_PROFILE_LINK,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen: ") + "Edit Mobile", CallbackCommands.SET_MOBILE,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen: ") + "Edit Car Plate", CallbackCommands.SET_CAR_PLATE,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen: ") + "Edit Pick Up", CallbackCommands.SET_PICKUP_LOC,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen: ") + "Edit Drop Off", CallbackCommands.SET_DROP_OFF_LOC,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen: ") + "Edit Route", CallbackCommands.SET_ROUTE,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen: ") + "Edit Seat", CallbackCommands.SET_AVAILABLE_SEAT,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen: ") + "Edit ETA", CallbackCommands.SET_ETA,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen: ") + "Edit ETD", CallbackCommands.SET_ETD,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":lower_left_ballpoint_pen: ") + "Edit Instruction", CallbackCommands.SET_INSTRUCTION,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":information_source: ") + "My Accounts", CallbackCommands.SHOW_MEMBER_INFO,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":bird: ") + "Request", CallbackCommands.POST_REQUEST,"");
		menuManager.addMenuItem(EmojiParser.parseToUnicode(":+1: ") + "Follow a Member", CallbackCommands.FOLLOW_MEMBER,"");
		menuManager.init();
		InlineKeyboardBuilder builder = menuManager.createMenuForPage(0, true);
		builder.setParse("HTML")
		.setChatId(chatId)
		.setText(ConstantMessage.showMyInformation(member,this.account, persistenceService)+ConstantMessage.PLEASE_CHOOSE_ACTION);
		return builder.build();
	}
}
