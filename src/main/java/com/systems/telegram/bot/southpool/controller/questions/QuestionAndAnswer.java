package com.systems.telegram.bot.southpool.controller.questions;

import java.time.LocalDateTime;

import com.systems.telegram.bot.southpool.entities.PreviousMessage;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberHomeToWork;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberWorkToHome;
import com.systems.telegram.bot.southpool.persistence.service.PersistenceService;
import com.systems.telegram.bot.southpool.utility.callback.CallbackCommands;
import com.systems.telegram.bot.southpool.utility.date.DateUtility;
import com.systems.telegram.bot.southpool.utility.message.ConstantMessage;
import com.systems.telegram.bot.southpool.utility.time.TimeUtility;

public class QuestionAndAnswer {

	public static void method(PersistenceService persistenceService, String username, String messageText) {

		PreviousMessage previousUserMessage = persistenceService.getMember(username, PreviousMessage.class);
		//Check if user is a registered member. If not, ask the user to register or exit the application.
		SouthPoolMemberHomeToWork southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
		SouthPoolMemberWorkToHome southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);

		if (ConstantMessage.ENTER_YOUR_NAME.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag())) {
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				southPoolMemberHomeToWork.setName(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				southPoolMemberWorkToHome.setName(messageText);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
		}
		else if (ConstantMessage.ENTER_YOUR_NAME.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				southPoolMemberWorkToHome.setName(messageText);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				southPoolMemberHomeToWork.setName(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
		}

		else if (ConstantMessage.ENTER_FACEBOOK_PROFILE_LINK.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag())) {
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				southPoolMemberHomeToWork.setFacebookProfileLink(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				southPoolMemberWorkToHome.setFacebookProfileLink(messageText);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
		}
		else if (ConstantMessage.ENTER_FACEBOOK_PROFILE_LINK.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				southPoolMemberWorkToHome.setFacebookProfileLink(messageText);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				southPoolMemberHomeToWork.setFacebookProfileLink(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
		}

		else if (ConstantMessage.ENTER_MOBILE_NUMBER.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag())) {
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				southPoolMemberHomeToWork.setMobileNumber(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				southPoolMemberWorkToHome.setMobileNumber(messageText);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
		}
		else if (ConstantMessage.ENTER_MOBILE_NUMBER.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				southPoolMemberWorkToHome.setMobileNumber(messageText);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				southPoolMemberHomeToWork.setMobileNumber(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
		}

		else if (ConstantMessage.ENTER_CAR_PLATE_NUMBER.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag())) {
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				southPoolMemberHomeToWork.setCarPlateNumber(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}

			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				southPoolMemberWorkToHome.setCarPlateNumber(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
		}
		else if (ConstantMessage.ENTER_CAR_PLATE_NUMBER.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				southPoolMemberWorkToHome.setCarPlateNumber(messageText);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				southPoolMemberHomeToWork.setCarPlateNumber(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
		}

		else if (ConstantMessage.ENTER_PICK_UP_LOCATION.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag())) {
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				southPoolMemberHomeToWork.setPicUpLoc(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
		}
		else if (ConstantMessage.ENTER_PICK_UP_LOCATION.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				southPoolMemberWorkToHome.setPicUpLoc(messageText);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
		}

		else if (ConstantMessage.ENTER_DROP_OFF_LOCATION.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag())) {
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				southPoolMemberHomeToWork.setDropOffLoc(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
		}
		else if (ConstantMessage.ENTER_DROP_OFF_LOCATION.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				southPoolMemberWorkToHome.setDropOffLoc(messageText);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
		}

		else if (ConstantMessage.ENTER_ROUTE.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag())) {
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				southPoolMemberHomeToWork.setRoute(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
		}
		else if (ConstantMessage.ENTER_ROUTE.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				southPoolMemberWorkToHome.setRoute(messageText);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
		}

		else if (ConstantMessage.ENTER_SEAT.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag())) {
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				southPoolMemberHomeToWork.setAvailableSlots(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
		}
		else if (ConstantMessage.ENTER_SEAT.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				southPoolMemberWorkToHome.setAvailableSlots(messageText);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
		}

		else if (ConstantMessage.ENTER_ETA.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag())) {
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				LocalDateTime localDateTime = DateUtility.toLocaDateTime(DateUtility.convertDateToGMT(8));
				messageText = TimeUtility.convertStandardTimeToMilitaryTime(messageText);
				String etaTime = messageText.contains(" AM") ? messageText.replace(" AM", "") : messageText.contains(" PM") ? messageText.replace(" PM", "") : messageText;
				String[] etaHHmm = etaTime.split(":");
				String dateETA = localDateTime.withHour(0).withMinute(0).withSecond(0).plusHours(Long.valueOf(etaHHmm[0])).plusMinutes(Long.valueOf(etaHHmm[1])).format(DateUtility.FORMAT_DATETIME);
				String dateETD = localDateTime.withHour(0).withMinute(0).withSecond(0).plusHours(Long.valueOf(etaHHmm[0])).plusMinutes(Long.valueOf(etaHHmm[1])).plusMinutes(15).format(DateUtility.FORMAT_DATETIME);
				southPoolMemberHomeToWork.setEta(dateETA);
				southPoolMemberHomeToWork.setEtd(dateETD);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
		}
		else if (ConstantMessage.ENTER_ETA.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				LocalDateTime localDateTime = DateUtility.toLocaDateTime(DateUtility.convertDateToGMT(8));
				messageText = TimeUtility.convertStandardTimeToMilitaryTime(messageText);
				String etaTime = messageText.contains(" AM") ? messageText.replace(" AM", "") : messageText.contains(" PM") ? messageText.replace(" PM", "") : messageText;
				String[] etaHHmm = etaTime.split(":");
				String dateETA = localDateTime.withHour(0).withMinute(0).withSecond(0).plusHours(Long.valueOf(etaHHmm[0])).plusMinutes(Long.valueOf(etaHHmm[1])).format(DateUtility.FORMAT_DATETIME);
				String dateETD = localDateTime.withHour(0).withMinute(0).withSecond(0).plusHours(Long.valueOf(etaHHmm[0])).plusMinutes(Long.valueOf(etaHHmm[1])).plusMinutes(15).format(DateUtility.FORMAT_DATETIME);
				southPoolMemberWorkToHome.setEta(dateETA);
				southPoolMemberWorkToHome.setEtd(dateETD);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
		}

		else if (ConstantMessage.ENTER_WAITING.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag())) {
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				LocalDateTime localDateTime = DateUtility.toLocaDateTime(southPoolMemberHomeToWork.getEta());
				String date = localDateTime.plusMinutes(Integer.valueOf(messageText.split("-")[0])).format(DateUtility.FORMAT_DATETIME);
				southPoolMemberHomeToWork.setEtd(date);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
		}
		else if (ConstantMessage.ENTER_WAITING.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberHomeToWork != null) {
				LocalDateTime localDateTime = DateUtility.toLocaDateTime(southPoolMemberWorkToHome.getEta());
				String date = localDateTime.plusMinutes(Integer.valueOf(messageText.split("-")[0])).format(DateUtility.FORMAT_DATETIME);
				southPoolMemberWorkToHome.setEtd(date);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
		}

		else if (ConstantMessage.ENTER_SPECIAL_MESSAGE.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.HOME2WORK.equals(previousUserMessage.getTag())) {
			southPoolMemberHomeToWork = persistenceService.getMember(username, SouthPoolMemberHomeToWork.class);
			if (southPoolMemberHomeToWork != null) {
				southPoolMemberHomeToWork.setCustomMessage(messageText);
				persistenceService.merge(southPoolMemberHomeToWork);
			}
		}
		else if (ConstantMessage.ENTER_SPECIAL_MESSAGE.equals(previousUserMessage.getPrevMessage()) && CallbackCommands.WORK2HOME.equals(previousUserMessage.getTag())) {
			southPoolMemberWorkToHome = persistenceService.getMember(username, SouthPoolMemberWorkToHome.class);
			if (southPoolMemberWorkToHome != null) {
				southPoolMemberWorkToHome.setCustomMessage(messageText);
				persistenceService.merge(southPoolMemberWorkToHome);
			}
		}
	}
}
