package com.systems.telegram.bot.southpool.utility.message;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.systems.telegram.bot.southpool.entities.Member;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberWorkToHome;
import com.systems.telegram.bot.southpool.utility.callback.CallbackCommands;
import com.systems.telegram.bot.southpool.utility.date.DateUtility;
import com.systems.telegram.bot.southpool.utility.time.TimeUtility;
import com.vdurmont.emoji.EmojiParser;

public class ConstantMessage {

	public static final String START = "/start";
	public static final String IOS = "IOS";
	public static final String ANDROID = "ANDROID";
	public static final String DESKTOP = "DESKTOP";
	
	public static final String REGISTER = "REGISTER";
	
	public static final String DRIVER = "DRIVER";
	public static final String PASSENGER = "PASSENGER";
	
	public static final String HOME2WORK = "Home to Work";
	public static final String WORK2HOME = "Work to Home";
	
	public static final String ACCOUNT_MESSAGE = "Please select which account you want to use:";
	
	public static final String SET_USERNAME_MESSAGE_POP_UP = "Sorry, but you need to set your username first before you can use this service.\n\n";
	public static final String SET_USERNAME_MESSAGE = "Sorry, but you need to set your username first before you can use this service.\n\n Please choose action:";
	
	public static final String SETUP_USERNAME_DONE_MESSAGE = " \nThen once you have done setting your telegram user name. You can proceed to member registration by clicking \"REGISTER\" button below.";
	public static final String IOS_LINK_MESSAGE = "Kindly click this link to see the steps on how to set a username in android device >> https://www.wikihow.com/Change-Your-Name-on-Telegram-on-Android \n";
	public static final String ANDROID_LINK_MESSAGE = "Kindly click this link to see the steps on how to set a username in ios device >> https://www.wikihow.com/Know-a-Chat-ID-on-Telegram-on-iPhone-or-iPad \n";
	public static final String DESKTOP_LINK_MESSAGE = "Kindly click this link to see the steps on how to set a username in desktop >> https://wavelet.atlassian.net/wiki/spaces/WMS/pages/21365027/How+to+change+username+in+Telegram \n";
	
	
	public static final String ENTER_YOUR_NAME = "\n\nPlease enter your name :";
	public static final String ENTER_FACEBOOK_PROFILE_LINK = "Enter your social network profile link :";
	public static final String ENTER_MOBILE_NUMBER = "Enter your mobile number :";
	public static final String ENTER_CAR_PLATE_NUMBER = "Enter your car plate number :";
	public static final String ENTER_WHAT_TYPE_OF_USER_YOU_ARE = "Are you a DRIVER or a PASSENGER ?";
	public static final String ENTER_PICK_UP_LOCATION = "Enter your pick up location :";
	public static final String ENTER_DROP_OFF_LOCATION = "Enter your drop off location :";
	public static final String ENTER_ROUTE = "Enter your route :";
	public static final String ENTER_SEAT = "Select how many seats you would like to offer or needed ?";
	public static final String ENTER_ETA = "Select your estimated time of arrival to your pick up point :";
	public static final String ENTER_WAITING = "Select your estimated waiting time before departure from your pick up point :";
	public static final String ENTER_SPECIAL_MESSAGE = "Enter your special message or instructions. Example: \"Please PM me for more questions.\" :";
	public static final String UPDATED = "Kindly review your information before posting. Thank you!";
	
	public static final String VERIFY_MEMBER = "Enter the telegram username of the member that you want to verify :\n";
	public static final String PLEASE_CHOOSE_ACTION = "\nPlease Choose action:";
	public static final String REPORT_TRAFFIC_STATUS = "Please enter any traffic related information that you want to share in the group.\n\n Example:\n oil price hike/rollback, traffic status or any MMDA operations:\n";
	public static final String REPORT_POSTED = "Your report was successfully posted in SOUTHPOOL telegram group carpooling community.Thank you!\n\n";
	public static final String COMPLAIN_MEMBER_PASSENGER_OR_DRIVER = "Please enter your concern to a passenger or to a driver. Your concern will be directly send to the southpool administors only:\n";
	public static final String POSTED_COMPLAIN_MEMBER_PASSENGER_OR_DRIVER = "Your complain was successfully sent to the SOUTHPOOL administrators. Rest assured that this will be reviewed by the admins and will give necessary action to your concern. Thank you for your cooperation.\n";
	public static final String BAN_MEMBER_TO_USE_THE_BOT = "Please enter the username of the member that you want to ban from using southpool :\n";
	public static final String BANNED = "User was successfully banned from using SOUTHPOOL telegram group carpooling community.Thank you!\n\n";
	public static final String FOLLOW_A_MEMBER = "Please enter the telegram username of the member that you want to follow :\n";
	public static final String ALREADY_FOLLOWED = "You are already following this member.Thank you!\n\n";
	public static final String FOLLOWED = "You have successfully followed this member. You will receive a notification whenever this member posted a request.Thank you!\n\n";
	
	
	public static final String BANNED_USER = "You are banned from using SOUTHPOOL telegram group carpooling community. Please contact the administrators. Thank you!\n\n";
	public static final String RESQUEST_MAX = "Sorry, you have reached the maximum allowable limit to post a request for this day.You may try again tomorrow.Thank you!\n\n";
	public static final String RESQUEST_POSTED = "Your request was successfully posted in SOUTHPOOL telegram group carpooling community.Thank you!\n\n";
	
	public static final String SEARCH_DRIVER = "Searching for DRIVER. Please wait...";
	public static final String SEARCH_PASSENGER = "Searching for PASSENGER. Please wait...";
	
	public static final String ADMIN_ONLY = "Only the administrators can ban a member. Please use \"Report a Member\" to report a member to Admins.Thank you!\n\n";
	
	static List<String> notAvailable = new ArrayList<>();

		
	private ConstantMessage(){}
	
	public static void initNotAvailableChecker() {
		notAvailable = new ArrayList<>();
		notAvailable.add("N/A");
		notAvailable.add("N/a");
		notAvailable.add("n/A");
		notAvailable.add("n/a");

		notAvailable.add("NA");
		notAvailable.add("na");
		notAvailable.add("Na");
		notAvailable.add("nA");

		notAvailable.add("NO");
		notAvailable.add("No");
		notAvailable.add("no");
		notAvailable.add("nO");

		notAvailable.add("None");
		notAvailable.add("none");
		notAvailable.add("Dont have");
		notAvailable.add("Not applicable");
		notAvailable.add(".");
		notAvailable.add("-");
		notAvailable.add("~");
		notAvailable.add("Null");
		notAvailable.add("null");
		notAvailable.add("No facebook");
		notAvailable.add("Not public on FB");
		notAvailable.add("Pass");
		notAvailable.add("I do not have facebook");
	}
	
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
	
	public static String showInfoToUpdateNext(Member member) {

		StringBuilder sb = new StringBuilder();
		if (member.getYouAre() == null) {
			String setYouAre = ENTER_WHAT_TYPE_OF_USER_YOU_ARE;
			sb.append(setYouAre);
			return sb.toString();
		}
		else if (member.getName() == null) {
			String setName = ENTER_YOUR_NAME;
			sb.append(setName);
			return sb.toString();
		}
		else if (member.getFacebookProfileLink() == null) {
			String setFaceBookProfile = ENTER_FACEBOOK_PROFILE_LINK;
			sb.append(setFaceBookProfile);
			return sb.toString();
		}
		else if (member.getMobileNumber() == null) {
			String setMobileNumber = ENTER_MOBILE_NUMBER;
			sb.append(setMobileNumber);
			return sb.toString();
		}
		else if ("DRIVER".equals(member.getYouAre()) && member.getCarPlateNumber() == null) {
			String setCarPlateNumber = ENTER_CAR_PLATE_NUMBER;
			sb.append(setCarPlateNumber);
			return sb.toString();
		}
		else if (member.getPicUpLoc() == null) {
			String setPickUpLocation = ENTER_PICK_UP_LOCATION;
			sb.append(setPickUpLocation);
			return sb.toString();
		}
		else if (member.getDropOffLoc() == null) {
			String setDropOffLocation = ENTER_DROP_OFF_LOCATION;
			sb.append(setDropOffLocation);
			return sb.toString();
		}
		else if (member.getRoute() == null) {
			String setRoute = ENTER_ROUTE;
			sb.append(setRoute);
			return sb.toString();
		}
		else if (member.getAvailableSlots() == null) {
			String setAvailableSeat = ENTER_SEAT;
			sb.append(setAvailableSeat);
			return sb.toString();
		}
		else if (member.getEta() == null) {
			String eta = ENTER_ETA;
			sb.append(eta);
			return sb.toString();
		}
		else if (member.getEtd() == null) {
			String etd = ENTER_WAITING;
			sb.append(etd);
			return sb.toString();
		}
		else if (member.getCustomMessage() == null) {
			String specialMessage = ENTER_SPECIAL_MESSAGE;
			sb.append(specialMessage);
			return sb.toString();
		}
		return UPDATED;
	}
	
	public static InlineKeyboardMarkup shownOptionsForDriverOrPassenger() {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		rowInline.add(new InlineKeyboardButton().setText("DRIVER").setCallbackData(CallbackCommands.DRIVER));
		rowInline.add(new InlineKeyboardButton().setText("PASSENGER").setCallbackData(CallbackCommands.PASSENGER));
		rowsInline.add(rowInline);
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}
	
	public static String verifyMember(Member member) {
		StringBuilder sb = new StringBuilder();
		sb.append("This member is verified as a " + member.getYouAre()).append("\n\n");
		return sb.toString();
	}
	
	public static String showMyInformation(Member member, String account) {
		
		String etaDate = DateUtility.toLocaDateTime(member.getEta()).format(DateUtility.FORMAT_DATETIME_INFO);
		String etdDate = DateUtility.toLocaDateTime(member.getEtd()).format(DateUtility.FORMAT_DATETIME_INFO);
		String etaTime = TimeUtility.convertToStandardTime(etaDate.split(" ")[1],etaDate.split(" ")[2]);
		String etdTime = TimeUtility.convertToStandardTime(etdDate.split(" ")[1],etdDate.split(" ")[2]);

		String date = DateUtility.toLocaDateTime(member.getEta()).format(DateUtility.MM_DD);
		String message = etaDate.contains("PM") ? " here later!" : " here for today!";

		if (DateUtility.toLocaDateTime(DateUtility.convertDateToGMT(8)).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(DateUtility.toLocaDateTime(member.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
			message = " here for tomorrow!";
		}

		String memberIcon = DRIVER.equals(member.getYouAre()) ? "🚘" : "😊";
		StringBuilder sb = new StringBuilder();
		sb.append(memberIcon).append("\n\n");
		sb.append(account+" ⊳  "+member.getYouAre()).append(message+" "+ date).append("\n");
		sb.append("@"+member.getUsername() + " - " + member.getName()).append("\n");
		if (!notAvailable.contains(member.getMobileNumber())) {
			sb.append("├<b> Mobile: </b>").append(member.getMobileNumber()).append("\n");	
		}
		if("DRIVER".equals(member.getYouAre())) {
			sb.append("├<b> Seat: </b>").append(member.getAvailableSlots()).append("\n");	
		}
		sb.append("└<b> ETD: </b>").append(etaTime +" - "+ etdTime).append("\n");
		if (!notAvailable.contains(member.getPicUpLoc())) {
			sb.append("\n⊳<b> Pick Up: </b>").append(member.getPicUpLoc()+"").append("\n");	
		}
		if (!notAvailable.contains(member.getDropOffLoc())) {
			sb.append("⊳<b> Drop Off: </b>").append(member.getDropOffLoc()+"").append("\n");	
		}
		if (!notAvailable.contains(member.getRoute())) {
			sb.append("⊳<b> Route: </b>").append(member.getRoute()+"").append("\n");	
		}
		
		if (!notAvailable.contains(member.getCustomMessage())) {
			sb.append("\n"+member.getCustomMessage()+"").append("\n");	
		}

		return sb.toString();
	}
	
	public static String showOrPostMyInformationToFollower(Member member) {

		String etaDate = DateUtility.toLocaDateTime(member.getEta()).format(DateUtility.FORMAT_DATETIME_INFO);
		String etdDate = DateUtility.toLocaDateTime(member.getEtd()).format(DateUtility.FORMAT_DATETIME_INFO);
		String etaTime = TimeUtility.convertToStandardTime(etaDate.split(" ")[1],etaDate.split(" ")[2]);
		String etdTime = TimeUtility.convertToStandardTime(etdDate.split(" ")[1],etdDate.split(" ")[2]);
		String date = DateUtility.toLocaDateTime(member.getEta()).format(DateUtility.MM_DD);
		
		String message = etaDate.contains("PM") ? " here later!" : " here for today!";
		if (DateUtility.toLocaDateTime(DateUtility.convertDateToGMT(8)).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(DateUtility.toLocaDateTime(member.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
			message = " here for tomorrow!";
		}

		String account = ConstantMessage.HOME2WORK;
		if (member instanceof SouthPoolMemberWorkToHome) {
			account = ConstantMessage.WORK2HOME;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(account+" ⊳  "+member.getYouAre()).append(message+" "+ date).append("\n");
		sb.append("@"+member.getUsername()).append("\n");
		if (!notAvailable.contains(member.getMobileNumber())) {
			sb.append("├<b> Mobile: </b>").append(member.getMobileNumber()).append("\n");	
		}
		if("DRIVER".equals(member.getYouAre())) {
			sb.append("├<b> Seat: </b>").append(member.getAvailableSlots()).append("\n");	
		}
		sb.append("└<b> ETD: </b>").append(etaTime +" - "+ etdTime).append("\n");
		if (!notAvailable.contains(member.getPicUpLoc())) {
			sb.append("\n⊳<b> Pick Up: </b>").append(member.getPicUpLoc()+"").append("\n");	
		}
		if (!notAvailable.contains(member.getDropOffLoc())) {
			sb.append("⊳<b> Drop Off: </b>").append(member.getDropOffLoc()+"").append("\n");	
		}
		if (!notAvailable.contains(member.getRoute())) {
			sb.append("⊳<b> Route: </b>").append(member.getRoute()+"").append("\n");	
		}
		
		if (!notAvailable.contains(member.getCustomMessage())) {
			sb.append("\n"+member.getCustomMessage()+"").append("\n");	
		}

		sb.append("\n\n\nUnfollow >> /unfollow__"+member.getUsername()).append("\n\n");
		sb.append("Start the Bot >> /start").append("\n");
		return sb.toString();
	}
	
	public static String notRegisteredMemberMessage() {
		String greyExclamation = EmojiParser.parseToUnicode(":grey_exclamation:");
		StringBuilder sb = new StringBuilder();
		sb.append(greyExclamation);
		sb.append("Sorry, but you need to register first before you can use this service.\n\n");
		return sb.toString();
	}

	public static InlineKeyboardMarkup shownOptions() {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		rowInline.add(new InlineKeyboardButton().setText(REGISTER).setCallbackData(CallbackCommands.REGISTER));
		rowInline.add(new InlineKeyboardButton().setText("HELP").setCallbackData(CallbackCommands.HELP));
		rowsInline.add(rowInline);// Set the keyboard to the markup
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}

	public static String showThankYou() {
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Thank you for using @southpoolservicebot").append("\n");
		messageBuilder.append("Contact if you have any questions, concerns or issues about the bot.\n");
		messageBuilder.append("👤 Creator").append("\n");
		messageBuilder.append("└ @OliverDela_cruzLundag)").append("\n");
		messageBuilder.append("👥 Admins").append("\n");
		messageBuilder.append("├ @Jinopedro").append("\n");
		messageBuilder.append("├ @b01nk3y").append("\n");
		messageBuilder.append("├ @chicolors)").append("\n");
		messageBuilder.append("├ @mrs_jump").append("\n");
		messageBuilder.append("├ @chaylandicho").append("\n");
		messageBuilder.append("├ @Iej555").append("\n");
		messageBuilder.append("├ @Hershey87").append("\n");
		messageBuilder.append("└ @JeffMendoza);").append("\n");
		messageBuilder.append("\n");
		messageBuilder.append("We'd love to hear all your feedback and suggestions.").append("\n");
		messageBuilder.append("Thank you for your cooperation.\n");
		return messageBuilder.toString();
	}
	
	public static String showThankUpdate() {
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Hi SOUTHPOOL members,").append("\n");
		messageBuilder.append("Good AM :)\n");
		messageBuilder.append("Happy to share with you some of the @southpoolservicebot functionality updates.\n\n");
		messageBuilder.append("1. Fix issues regarding incorrect date when posting a request for today or for tomorrow.\n\n");
		messageBuilder.append("2. Fix Search feature.\n\n");
		messageBuilder.append("3. To get the latest updates just type /start in the chat window of the bot (@southpoolservicebot) and then press enter.\n\n");
		messageBuilder.append(" \n\n");
		messageBuilder.append("Contact if you have any questions, concerns or issues about the bot.\n");
		messageBuilder.append("👤 Creator").append("\n");
		messageBuilder.append("└ @OliverDela_cruzLundag)").append("\n");
		messageBuilder.append("👥 Admins").append("\n");
		messageBuilder.append("├ @Jinopedro").append("\n");
		messageBuilder.append("├ @b01nk3y").append("\n");
		messageBuilder.append("├ @chicolors)").append("\n");
		messageBuilder.append("├ @mrs_jump").append("\n");
		messageBuilder.append("├ @chaylandicho").append("\n");
		messageBuilder.append("├ @Iej555").append("\n");
		messageBuilder.append("├ @Hershey87").append("\n");
		messageBuilder.append("└ @JeffMendoza);").append("\n");
		messageBuilder.append("\n");
		messageBuilder.append("Thank you for your cooperation.\n");
		return messageBuilder.toString();
	}
}
