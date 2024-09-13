package com.systems.telegram.bot.southpool.utility.message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import com.systems.telegram.bot.southpool.controller.comment.response.Comment;
import com.systems.telegram.bot.southpool.entities.Followers;
import com.systems.telegram.bot.southpool.entities.Member;
import com.systems.telegram.bot.southpool.entities.MemberStar;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberHomeToWork;
import com.systems.telegram.bot.southpool.entities.SouthPoolMemberWorkToHome;
import com.systems.telegram.bot.southpool.persistence.service.PersistenceService;
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
	
	public static final String CLICK_TO_JOIN = "Click to join";
	public static final String ACCOUNT_MESSAGE = "Please select which account you want to use:";
	
	public static final String SET_USERNAME_MESSAGE_POP_UP = "Sorry, but you need to set your username first before you can use this service.\n\n";
	public static final String ALREADY_JOINED = "You are already a member of SOUTHPOOL carpooling community.\n\n";
	public static final String ALREADY_SPDL_JOINED = "You are already a member of Southpool Driver's Lounge community.\n\n";
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
	public static final String COMPLAIN_MEMBER_PASSENGER_OR_DRIVER = "Please enter your concern to a passenger or to a driver or any other concern related to SOUTHPOOL. This will be directly send to the southpool administors only:\n";
	public static final String POSTED_COMPLAIN_MEMBER_PASSENGER_OR_DRIVER = "Your complain was successfully sent to the SOUTHPOOL administrators. Rest assured that this will be reviewed by the admins and will give necessary action to your concern. Thank you for your cooperation.\n";
	public static final String BAN_MEMBER_TO_USE_THE_BOT = "Please enter the username of the member that you want to ban from using southpool :\n";
	public static final String RESET_MEMBER_ACCOUNT = "Please enter the username of the member that you want to reset the account :\n";
	public static final String RESET_MEMBER_REQUEST = "Please enter the username of the member that you want to reset the request count :\n";
	public static final String BANNED = "User was successfully banned from using SOUTHPOOL telegram group carpooling community.Thank you!\n\n";
	public static final String RESET = "User account was successfully reset!\n\n";
	public static final String RESET_REQUEST = "User request count was successfully reset!\n\n";
	public static final String FOLLOW_A_MEMBER = "Please enter the telegram username of the member that you want to follow :\n";
	public static final String ALREADY_FOLLOWED = "You are already following this member.Thank you!\n\n";
	public static final String FOLLOWED = "You have successfully followed this member. You will receive a notification whenever this member posted a request.Thank you!\n\n";
	
	
	public static final String BANNED_USER = "You are banned from using SOUTHPOOL telegram group carpooling community. Please contact the administrators. Thank you!\n\n";
	public static final String RESQUEST_MAX = "Sorry, you have reached the maximum allowable limit to post a request for this day.You may try again tomorrow.Thank you!\n\n";
	public static final String RESQUEST_POSTED = "Your request was successfully posted in SOUTHPOOL telegram group carpooling community.Thank you!\n\n";
	
	public static final String SEARCH_DRIVER = "Searching for DRIVER. Please wait...";
	public static final String SEARCH_PASSENGER = "Searching for PASSENGER. Please wait...";
	
	public static final String GIVE_STAR_MESSAGE = "Enter the telegram username of the member that you want to give a star :\n";
	public static final String UNLIKE_MEMBER_MESSAGE = "Enter the telegram username of the member that you want to unlike :\n";
	public static final String CONVERT_STAR_MESSAGE = "How many stars would you like to convert to post request? (1 star is equal to 1 post request) :\n";
	
	public static final String ADMIN_ONLY = "Only the administrators can ban a member. Please use \"Report a Member\" to report a member to Admins.Thank you!\n\n";
	public static final String NOT_ALLOWED_TO_GIVE_STAR_ON_OWNSELF = "You were not allowed to give star on your ownself!\n\n";
	public static final String NOT_ALLOWED_TO_UNLIKE_YOUR_OWNSELF = "You were not allowed to unlike your ownself!\n\n";
	public static final String SEND_STAR_SUCCESS = "Star was succefully sent to ";
	public static final String SEND_UNLIKE_SUCCESS = "Done!";
	
	public static final String CONVERSION_SUCCESS = "Stars to Post Request Count was succefully converted!";
	
	public static final String CREATE_PROFILE = "Send me one photo for your profile :\n";
	
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
		
		InlineKeyboardButton ios = new InlineKeyboardButton();
		ios.setText(IOS);
		ios.setCallbackData(CallbackCommands.IOS);
		
		InlineKeyboardButton android = new InlineKeyboardButton();
		ios.setText(ANDROID);
		ios.setCallbackData(CallbackCommands.ANDROID);
		
		InlineKeyboardButton desktop = new InlineKeyboardButton();
		desktop.setText(DESKTOP);
		desktop.setCallbackData(CallbackCommands.DESKTOP);
		
		rowInline.add(ios);
		rowInline.add(android);
		rowInline.add(desktop);
		rowsInline.add(rowInline);
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}
	
	public static InlineKeyboardMarkup shownOptionsForWorkAndHomeInfo() {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		
		InlineKeyboardButton h2w = new InlineKeyboardButton();
		h2w.setText(HOME2WORK);
		h2w.setCallbackData(CallbackCommands.HOME2WORK);
		
		InlineKeyboardButton w2h = new InlineKeyboardButton();
		w2h.setText(WORK2HOME);
		w2h.setCallbackData(CallbackCommands.WORK2HOME);
		
		rowInline.add(h2w);
		rowInline.add(w2h);
		rowsInline.add(rowInline);
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}
	
	public static InlineKeyboardMarkup openCreatePageLink(String url) {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		
		InlineKeyboardButton upp = new InlineKeyboardButton();
		upp.setText("Upload Profile Photo");
		upp.setUrl(url);
		rowInline.add(upp);
		rowsInline.add(rowInline);
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}
	
	public static InlineKeyboardMarkup openCommentPageLink(String url) {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		InlineKeyboardButton rw = new InlineKeyboardButton();
		rw.setText("Review Member");
		rw.setUrl(url);
		rowInline.add(rw);
		rowsInline.add(rowInline);
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}
	
	public static InlineKeyboardMarkup openMyCommentPageLink(String url) {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		InlineKeyboardButton mp = new InlineKeyboardButton();
		mp.setText("My Profile");
		mp.setUrl(url);
		rowInline.add(new InlineKeyboardButton());
		rowsInline.add(rowInline);
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}
	
	public static InlineKeyboardMarkup joinSouthPoolGroup(String url) {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		InlineKeyboardButton js = new InlineKeyboardButton();
		js.setText("Join SouthPool");
		js.setUrl(url);
		rowInline.add(js);
		rowsInline.add(rowInline);
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}
	
	public static InlineKeyboardMarkup joinSouthPoolDriverLoungeGroup(String url) {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		InlineKeyboardButton jsdl = new InlineKeyboardButton();
		jsdl.setText("Join SouthPool Driver's Lounge");
		jsdl.setUrl(url);
		rowInline.add(jsdl);
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
		InlineKeyboardButton d = new InlineKeyboardButton();
		d.setText("DRIVER");
		d.setCallbackData(CallbackCommands.DRIVER);
		
		InlineKeyboardButton p = new InlineKeyboardButton();
		p.setText("PASSENGER");
		p.setCallbackData(CallbackCommands.PASSENGER);
		
		rowInline.add(d);
		rowInline.add(p);
		rowsInline.add(rowInline);
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}
	
	public static String verifyMember(Member member, long star, int followers) {
		String stars = EmojiParser.parseToUnicode(":star:");
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String registered = dt.format(member.getRegisterDate());
		StringBuilder sb = new StringBuilder();
		sb.append("@"+member.getUsername() + " is a verified southpool " + member.getYouAre()).append(" with <b>").append(star).append("</b>").append(" ").append(stars).append(".\n");
		if (followers > 0) {
			if (followers == 1) {
				sb.append("Member since <b>" + registered).append("</b> with <b>").append(followers).append("</b> follower.");	
			}
			else {
				sb.append("Member since <b>" + registered).append("</b> with <b>").append(followers).append("</b> followers.");	
			}
		}
		else {
			sb.append("Member since <b>" + registered).append("</b>");
		}
		return sb.toString();
	}
	
	public static String showMyInformation(Member member, String account, PersistenceService persistenceService) {
		
		Map<String,String> uniqueConstraintNameValueMap = new HashMap<>();
		uniqueConstraintNameValueMap.put("username", member.getUsername());
		long starCount = 0;
		if (persistenceService.findByUniqueConstraint(uniqueConstraintNameValueMap, MemberStar.class)) {
			starCount = persistenceService.getMember(member.getUsername(), MemberStar.class).getStar();
		}
		
		String etaDate = DateUtility.toLocaDateTime(member.getEta()).format(DateUtility.FORMAT_DATETIME_INFO);
		String etdDate = DateUtility.toLocaDateTime(member.getEtd()).format(DateUtility.FORMAT_DATETIME_INFO);
		String etaTime = TimeUtility.convertToStandardTime(etaDate.split(" ")[1],etaDate.split(" ")[2]);
		String etdTime = TimeUtility.convertToStandardTime(etdDate.split(" ")[1],etdDate.split(" ")[2]);

		String date = DateUtility.toLocaDateTime(member.getEta()).format(DateUtility.MM_DD);
		String message = etaDate.contains("PM") ? " here for later!" : " here for today!";

		if (DateUtility.toLocaDateTime(DateUtility.convertDateToGMT(8)).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(DateUtility.toLocaDateTime(member.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
			message = " here for tomorrow!";
		}

		int seatCount;
		try {
			seatCount = Integer.valueOf(member.getAvailableSlots());
		}catch (Exception e) {
			seatCount = 1;
		}
		
		StringBuilder seat = new StringBuilder();
		if (member.getAvailableSlots() != null) {
			for (int i = 0; i<seatCount; i++) {
				seat.append("💺");
			}	
		}
		
		String home2Work = EmojiParser.parseToUnicode(":house: ")+EmojiParser.parseToUnicode(":arrow_right: ")+EmojiParser.parseToUnicode(":office:");
		String work2Home = EmojiParser.parseToUnicode(":house: ")+EmojiParser.parseToUnicode(":arrow_left: ")+EmojiParser.parseToUnicode(":office:");
		
		String accountIcon = "Work to Home".equals(account) ? work2Home : home2Work;
		
		String memberIcon = DRIVER.equals(member.getYouAre()) ? "🚘" : "😊";
		String star = EmojiParser.parseToUnicode(":star:");
		StringBuilder sb = new StringBuilder();
		sb.append(memberIcon).append(" : " + star + " " + starCount).append("\n");
		sb.append(accountIcon+"\n"+account+" : "+member.getYouAre()).append(message+" "+ date).append("\n");
		sb.append("@"+member.getUsername() + " - " + member.getName()).append("\n");
//		if (!notAvailable.contains(member.getMobileNumber())) {
//			sb.append("├ Mobile: ").append(member.getMobileNumber()).append("\n");	
//		}
		if("DRIVER".equals(member.getYouAre()) && member.getAvailableSlots() != null) {
			sb.append("├ Seat: ").append(member.getAvailableSlots()).append(" "+seat.toString()).append("\n");	
		}
		sb.append("└ ETD: ").append(etaTime +" - "+ etdTime).append("\n");
		if (!notAvailable.contains(member.getPicUpLoc())) {
			sb.append("\n⊳<b> Pick Up: </b>").append(member.getPicUpLoc()+"").append("\n");	
		}
		if (!notAvailable.contains(member.getDropOffLoc())) {
			sb.append("⊳<b> Drop Off: </b>").append(member.getDropOffLoc()+"").append("\n");	
		}
//		if (!notAvailable.contains(member.getRoute())) {
//			sb.append("⊳<b> Route: </b>").append(member.getRoute()+"").append("\n");	
//		}
		
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
		
		String message = etaDate.contains("PM") ? " here for later!" : " here for today!";
		if (DateUtility.toLocaDateTime(DateUtility.convertDateToGMT(8)).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(DateUtility.toLocaDateTime(member.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
			message = " here for tomorrow!";
		}

		String account = ConstantMessage.HOME2WORK;
		if (member instanceof SouthPoolMemberWorkToHome) {
			account = ConstantMessage.WORK2HOME;
		}
		
		String home2Work = EmojiParser.parseToUnicode(":house: ")+EmojiParser.parseToUnicode(":arrow_right: ")+EmojiParser.parseToUnicode(":office:");
		String work2Home = EmojiParser.parseToUnicode(":house: ")+EmojiParser.parseToUnicode(":arrow_left: ")+EmojiParser.parseToUnicode(":office:");
		
		String accountIcon = "Work to Home".equals(account) ? work2Home : home2Work;
		
		StringBuilder sb = new StringBuilder();
		sb.append(accountIcon+"\n"+account+" ⊳  "+member.getYouAre()).append(message+" "+ date).append("\n");
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

		sb.append("\nUnfollow >> /unfollow__"+member.getUsername()).append("\n\n");
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
		
		InlineKeyboardButton r = new InlineKeyboardButton();
		r.setText(REGISTER);
		r.setCallbackData(CallbackCommands.REGISTER);
		
		InlineKeyboardButton h = new InlineKeyboardButton();
		h.setText("HELP");
		h.setCallbackData(CallbackCommands.HELP);
		
		rowInline.add(r);
		rowInline.add(h);
		rowsInline.add(rowInline);// Set the keyboard to the markup
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}
	
	public static String showCreateProfileSteps() {
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Steps on how to create a southpool profile:").append("\n");
		messageBuilder.append("1. Search and add @CommentsBot to your telegram contact. kindly type /start in @CommentsBot chat window then enter.").append("\n");
		messageBuilder.append("2. Go back to @southpoolservicebot then click 'My Account' button -  choose either 'Work to Home' or 'Home to Work' then click 'Create Profile' button.").append("\n");
		messageBuilder.append("3. Click 'Upload Profile Photo' button - an external site will open, complete your profile and add photo then click 'Publish'. After that, go back to @southpoolservicebot again.").append("\n");
		messageBuilder.append("5. Click 'My Account' button -  choose either 'Work to Home' or 'Home to Work'. The Publish profile button should appear.").append("\n");
		messageBuilder.append("6. Click 'Publish Profile' button - If it goes through, the 'My Profile' button should appear else, contact the admins for help.").append("\n");
		messageBuilder.append("7. Click 'My Profile' - You should see profile after clicking 'My Profile'.").append("\n");
		return messageBuilder.toString();
	}
	
	public static String showThankYou() {
		StringBuilder messageBuilder = new StringBuilder();
		messageBuilder.append("Thank you for using @southpoolservicebot").append("\n");
		messageBuilder.append("Contact if you have any questions, concerns or issues about the bot.\n");
		messageBuilder.append("👤 Creators").append("\n");
		messageBuilder.append("├ @mr_jump").append("\n");
		messageBuilder.append("└ @mrs_jump").append("\n");
		messageBuilder.append("👥 Admins").append("\n");
		messageBuilder.append("├ @Iej555").append("\n");
		messageBuilder.append("├ @b01nk3y").append("\n");
		messageBuilder.append("├ @Jinopedro").append("\n");
		messageBuilder.append("└ @JeffMendoza").append("\n");
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
		messageBuilder.append("└ @mr_jump").append("\n");
		messageBuilder.append("👥 Admins").append("\n");
		messageBuilder.append("├ @Jinopedro").append("\n");
		messageBuilder.append("├ @b01nk3y").append("\n");
		messageBuilder.append("├ @chicolors").append("\n");
		messageBuilder.append("├ @mrs_jump").append("\n");
		messageBuilder.append("├ @chaylandicho").append("\n");
		messageBuilder.append("├ @Iej555").append("\n");
		messageBuilder.append("└ @JeffMendoza").append("\n");
		messageBuilder.append("\n");
		messageBuilder.append("Thank you for your cooperation.\n");
		return messageBuilder.toString();
	}
	
	public static String showMyInformationWithComment(Comment comment, Member member, String account, PersistenceService persistenceService) {
		
		Map<String,String> uniqueConstraintNameValueMap = new HashMap<>();
		uniqueConstraintNameValueMap.put("username", member.getUsername());
		long starCount = 0;
		long unlikes = 0;
		
		if (persistenceService.findByUniqueConstraint(uniqueConstraintNameValueMap, MemberStar.class)) {
			starCount = persistenceService.getMember(member.getUsername(), MemberStar.class).getStar();
			unlikes = persistenceService.getMember(member.getUsername(), MemberStar.class).getUnlike();
		}
		
		String etaDate = DateUtility.toLocaDateTime(member.getEta()).format(DateUtility.FORMAT_DATETIME_INFO);
		String etdDate = DateUtility.toLocaDateTime(member.getEtd()).format(DateUtility.FORMAT_DATETIME_INFO);
		String etaTime = TimeUtility.convertToStandardTime(etaDate.split(" ")[1],etaDate.split(" ")[2]);
		String etdTime = TimeUtility.convertToStandardTime(etdDate.split(" ")[1],etdDate.split(" ")[2]);

		String date = DateUtility.toLocaDateTime(member.getEta()).format(DateUtility.MM_DD);
		String message = etaDate.contains("PM") ? " here for later!" : " here for today!";

		if (DateUtility.toLocaDateTime(DateUtility.convertDateToGMT(8)).withHour(0).withMinute(0).withSecond(0).withNano(0).isBefore(DateUtility.toLocaDateTime(member.getEta()).withHour(0).withMinute(0).withSecond(0).withNano(0))) {
			message = " here for tomorrow!";
		}

		int seatCount;
		try {
			seatCount = Integer.valueOf(member.getAvailableSlots());
		}catch (Exception e) {
			seatCount = 1;
		}
		
		StringBuilder seat = new StringBuilder();
		if (member.getAvailableSlots() != null) {
			for (int i = 0; i<seatCount; i++) {
				seat.append("💺");
			}	
		}
		String rightArrow = EmojiParser.parseToUnicode(":arrow_right: ");
		String leftArrow = EmojiParser.parseToUnicode(":arrow_left: ");
		String home2Work = EmojiParser.parseToUnicode(":house: ")+rightArrow+rightArrow+EmojiParser.parseToUnicode(":office:");
		String work2Home = EmojiParser.parseToUnicode(":house: ")+leftArrow+leftArrow+EmojiParser.parseToUnicode(":office:");
		
		String accountIcon = "Work to Home".equals(account) ? work2Home : home2Work;
		
		String memberIcon = DRIVER.equals(member.getYouAre()) ? EmojiParser.parseToUnicode(":oncoming_automobile:") : EmojiParser.parseToUnicode(":running:");
		String star = EmojiParser.parseToUnicode(":star:");
		String unlike = EmojiParser.parseToUnicode(":-1:");
		StringBuilder sb = new StringBuilder();
		sb.append(memberIcon).append(" : " + star + " " + starCount + " : " ).append(unlike + " " + unlikes).append("\n");
		sb.append(accountIcon+" "+account+"\n"+member.getYouAre()).append(message+" "+ date).append("\n");
		sb.append(EmojiParser.parseToUnicode(":bust_in_silhouette:")).append("@"+member.getUsername()).append(" - " + member.getName()).append("\n");
		if("DRIVER".equals(member.getYouAre()) && member.getAvailableSlots() != null) {
			sb.append("├ Seat: ").append(member.getAvailableSlots()).append(" "+seat.toString()).append("\n");	
		}
		sb.append("└ ETD: ").append(etaTime +" - "+ etdTime).append("\n");
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
		if (comment != null) {
			if (comment.getOk()) {
				sb.append("\n<a href=").append("\"").append(comment.getResult().getLink()).append("\">").append(EmojiParser.parseToUnicode(":calling:")).append("comment</a>");
				if (member.getProfilePostLink() != null) {
					sb.append("<a href=").append("\"").append(member.getProfilePostLink()).append("\">").append("\t"+EmojiParser.parseToUnicode(":bust_in_silhouette:")).append("profile</a>");	
				}
				sb.append("\n"+EmojiParser.parseToUnicode(":oncoming_automobile:")).append(" @southpoolservicebot");	
			}	
		}
		return sb.toString();
	}
	
	
	public static String showMyProfile(Member member, String account, PersistenceService persistenceService) {
		
		SouthPoolMemberHomeToWork southPoolMemberHomeToWork = persistenceService.getMember(member.getUsername(), SouthPoolMemberHomeToWork.class);
		SouthPoolMemberWorkToHome southPoolMemberWorkToHome = persistenceService.getMember(member.getUsername(), SouthPoolMemberWorkToHome.class);
		
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String registered = dt.format(member.getRegisterDate());
		
		Map<String,String> uniqueConstraintNameValueMap = new HashMap<>();
		uniqueConstraintNameValueMap.put("username", member.getUsername());
		uniqueConstraintNameValueMap.put("active", "Y");
		List<Followers> followers = persistenceService.getFolowerBy(uniqueConstraintNameValueMap, Followers.class);
		int followerSize = followers.size();
		
		Map<String,String> uniqueConstraintNameValueMapStar = new HashMap<>();
		uniqueConstraintNameValueMap.put("username", member.getUsername());
		long starCount = persistenceService.getMembersBy(uniqueConstraintNameValueMapStar, MemberStar.class).isEmpty() ? 0 : persistenceService.getMembersBy(uniqueConstraintNameValueMapStar, MemberStar.class).get(0).getStar();
		
		String memberIcon = DRIVER.equals(member.getYouAre()) ? "🚘" : "😊";
		String star = EmojiParser.parseToUnicode(":star:");
		StringBuilder sb = new StringBuilder();
		sb.append(memberIcon).append(" : " + star + " " + starCount).append("\n");
		sb.append("Account: <b>"+member.getYouAre()).append("</b>\n");
		sb.append("@"+member.getUsername() + " - " + member.getName()).append("\n");
		if (followerSize > 0) {
			if (followerSize == 1) {
				sb.append("Member since <b>" + registered).append("</b> with <b>").append(followerSize).append("</b> follower.\n");	
			}
			else {
				sb.append("Member since <b>" + registered).append("</b> with <b>").append(followerSize).append("</b> followers.\n");	
			}
		}
		else {
			sb.append("Member since <b>" + registered).append("</b>\n\n");
		}
		
		
		sb.append("\n<b>").append(ConstantMessage.HOME2WORK).append("</b>");	
		if (!notAvailable.contains(southPoolMemberHomeToWork.getPicUpLoc())) {
			sb.append("\n⊳<b> Pick Up: </b>").append(southPoolMemberHomeToWork.getPicUpLoc()+"").append("\n");	
		}
		if (!notAvailable.contains(southPoolMemberHomeToWork.getDropOffLoc())) {
			sb.append("⊳<b> Drop Off: </b>").append(southPoolMemberHomeToWork.getDropOffLoc()+"").append("\n");	
		}
		if (!notAvailable.contains(southPoolMemberHomeToWork.getRoute())) {
			sb.append("⊳<b> Route: </b>").append(southPoolMemberHomeToWork.getRoute()+"").append("\n");	
		}
		
		sb.append("\n<b>").append(ConstantMessage.WORK2HOME).append("</b>");	
		if (!notAvailable.contains(southPoolMemberWorkToHome.getPicUpLoc())) {
			sb.append("\n⊳<b> Pick Up: </b>").append(southPoolMemberWorkToHome.getPicUpLoc()+"").append("\n");	
		}
		if (!notAvailable.contains(southPoolMemberWorkToHome.getDropOffLoc())) {
			sb.append("⊳<b> Drop Off: </b>").append(southPoolMemberWorkToHome.getDropOffLoc()+"").append("\n");	
		}
		if (!notAvailable.contains(southPoolMemberWorkToHome.getRoute())) {
			sb.append("⊳<b> Route: </b>").append(southPoolMemberWorkToHome.getRoute()+"").append("\n");	
		}
		if (!notAvailable.contains(southPoolMemberWorkToHome.getCustomMessage())) {
			sb.append("\n"+southPoolMemberWorkToHome.getCustomMessage()+"").append("\n");	
		}

		return sb.toString();
	}
}
