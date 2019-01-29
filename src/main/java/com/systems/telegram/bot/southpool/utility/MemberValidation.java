package com.systems.telegram.bot.southpool.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.systems.telegram.bot.southpool.entities.Member;
import com.systems.telegram.bot.southpool.entities.PreviousMessage;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberHomeToWork;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberWorkToHome;
import com.systems.telegram.bot.southpool.persistence.service.PersistenceService;
import com.systems.telegram.bot.southpool.utility.date.DateUtility;

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
	
	public static void updateUserMemberETAandETDInfo(PersistenceService persistenceService, Member member) {
		if (DateUtility.toLocaDateTime(member.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(DateUtility.toLocaDateTime(DateUtility.convertDateToGMT(8)).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
			String dateToday = DateUtility.toLocaDateTime(DateUtility.convertDateToGMT(8)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			String[] etaTime = member.getEta().split(" ");
			String eta = dateToday + " " + etaTime[1];

			String[] etdTime = member.getEtd().split(" ");
			String etd = dateToday + " " + etdTime[1];

			member.setEta(eta);
			member.setEtd(etd);
			persistenceService.merge(member);
		}
		else if (DateUtility.toLocaDateTime(member.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isAfter(DateUtility.toLocaDateTime(DateUtility.convertDateToGMT(8)).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
			String dateToday = DateUtility.toLocaDateTime(member.getEta()).minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			String[] etaTime = member.getEta().split(" ");
			String eta = dateToday + " " + etaTime[1];

			String[] etdTime = member.getEtd().split(" ");
			String etd = dateToday + " " + etdTime[1];

			member.setEta(eta);
			member.setEtd(etd);
			persistenceService.merge(member);
		}
	}
	
	public static void updateUserMemberETAandETDInfoForTomorrow(PersistenceService persistenceService, Member member, String username) {
		if (DateUtility.toLocaDateTime(member.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(DateUtility.toLocaDateTime(DateUtility.convertDateToGMT(8)).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
			String dateToday = DateUtility.toLocaDateTime(DateUtility.convertDateToGMT(8)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			String[] etaTime = member.getEta().split(" ");
			String eta = dateToday + " " + etaTime[1];

			String[] etdTime = member.getEtd().split(" ");
			String etd = dateToday + " " + etdTime[1];

			member.setEta(eta);
			member.setEtd(etd);
			persistenceService.merge(member);
		}
		
		member = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
		if (DateUtility.toLocaDateTime(DateUtility.convertDateToGMT(8)).withHour(0).withMinute(0).withSecond(0).withNano(0).isEqual(DateUtility.toLocaDateTime(member.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
			LocalDateTime localDateTimeETA = DateUtility.toLocaDateTime(member.getEta());
			LocalDateTime localDateTimeETD = DateUtility.toLocaDateTime(member.getEtd());
			String dateETA = localDateTimeETA.plusDays(1).format(DateUtility.FORMAT_DATETIME);
			String dateETD = localDateTimeETD.plusDays(1).format(DateUtility.FORMAT_DATETIME);
			member.setEta(dateETA);
			member.setEtd(dateETD);
			persistenceService.merge(member);
		}
	}
	
}
