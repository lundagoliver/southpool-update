package com.systems.telegram.bot.southpool.utility;

import com.systems.telegram.bot.southpool.entities.Member;

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
	
}
