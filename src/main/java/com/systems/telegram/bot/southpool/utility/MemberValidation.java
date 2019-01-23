package com.systems.telegram.bot.southpool.utility;

import com.systems.telegram.bot.southpool.entities.Member;
import com.systems.telegram.bot.southpool.entities.PreviousMessage;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberHomeToWork;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberWorkToHome;
import com.systems.telegram.bot.southpool.persistence.service.PersistenceService;

public class MemberValidation {

	private MemberValidation(){}
	
	public static boolean isInfoNotComplete(Member southPoolMemberHomeToWork) {
		if(southPoolMemberHomeToWork.getName() == null || 
				southPoolMemberHomeToWork.getFacebookProfileLink() == null || 
				southPoolMemberHomeToWork.getMobileNumber() ==  null || 
				southPoolMemberHomeToWork.getCarPlateNumber() ==  null || 
				southPoolMemberHomeToWork.getYouAre() == null || 
				southPoolMemberHomeToWork.getPicUpLoc() == null || 
				southPoolMemberHomeToWork.getDropOffLoc() == null ||
				southPoolMemberHomeToWork.getRoute() == null ||
				southPoolMemberHomeToWork.getAvailableSlots() == null || 
				southPoolMemberHomeToWork.getEta() == null ||
				southPoolMemberHomeToWork.getEtd() == null || 
				southPoolMemberHomeToWork.getCustomMessage() == null) {
			return true;
		}
		return false;
	}
	
	public static void updateUserPreviousMessage(PersistenceService persistenceService, Member previousMessage, String username, String tag) {
		if (previousMessage == null) {
			previousMessage = new PreviousMessage();
			previousMessage.setUsername(username);
			previousMessage.setTag(tag);
			persistenceService.persist(previousMessage);
		}
		else {
			previousMessage = persistenceService.getMember(username, PreviousMessage.class);
			previousMessage.setTag(tag);
			persistenceService.merge(previousMessage);
		}	
	}
	
	@SuppressWarnings("unchecked")
	public static String saveAndSendMessage(PersistenceService persistenceService, String botQuestion, PreviousMessage previousMessage, String username, @SuppressWarnings("rawtypes") Class clazz) {
		previousMessage = (PreviousMessage) persistenceService.getMember(username, clazz);
		if (previousMessage == null) {
			previousMessage = new PreviousMessage();
			previousMessage.setUsername(username);
			previousMessage.setPrevMessage(botQuestion);
			persistenceService.persist(previousMessage);
		}
		else {
			previousMessage.setPrevMessage(botQuestion);
			persistenceService.merge(previousMessage);
		}
		return botQuestion;
	}
	
	public static void updateUserChatId(PersistenceService persistenceService, long chatId, String username) {
		
		SouthPoolMemberHomeToWork southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
		SouthPoolMemberWorkToHome southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
		
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
	}
	
}
